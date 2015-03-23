package scamsg

import akka.actor._

class Server extends Actor {
  
	var clients = List[(String, ActorRef)]();
    
    def receive = {
      case Connect(username) => {
    	  broadcast(Info(f"$username%s joined the chat"))
    	  clients = (username,sender) :: clients
    	  context.watch(sender)
        }
      case Broadcast(msg) => {
    	  val username = getUsername(sender)
          broadcast(NewMsg(username, msg))
      }
      case Terminated(client) => {
        val username = getUsername(client)
        clients = clients.filter(sender != _._2)
        broadcast(Info(f"$username%s left the chat"))
      }
    }
    
    def broadcast(msg: Msg) {
    	clients.foreach(x => x._2 ! msg)
    }

    def getUsername(actor: ActorRef): String = {
	clients.filter(actor == _._2).head._1
    }
 }

class Client(val username: String, server: ActorRef) extends Actor {
  
  server ! Connect(username)
  
  def receive = {
    case NewMsg(from, msg) => {
      println(f"[$username%s's client] - $from%s: $msg%s")
    }
    case Send(msg) => server ! Broadcast(msg)
    case Info(msg) => {
      println(f"[$username%s's client] - $msg%s")
    }
    case Disconnect => {
      self ! PoisonPill
    }
  } 
}

abstract class Msg
case class Send(msg: String) extends Msg
case class NewMsg(from: String, msg: String) extends Msg
case class Info(msg: String) extends Msg

case class Connect(username: String) extends Msg
case class Broadcast(msg: String) extends Msg
case object Disconnect extends Msg



