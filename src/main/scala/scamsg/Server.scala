package scamsg

import akka.actor._
import akka.actor

class Server extends Actor {
  
	var clients = List[(String, ActorRef)]();
    
    def receive = {
      
      case Connect(username) => {
    	  clients.foreach(x => x._2 ! Info(f"$username%s joined the chat"))
    	  clients = (username,sender) :: clients
        }
      case Msg(msg) => {
    	  val username = clients.filter(sender == _._2).head._1
          clients.foreach(x => x._2 ! NewMsg(username, msg))
      }
      case Disconnect => {
        val username = clients.filter(sender == _._2).head._1
        clients = clients.filter(sender != _._2)
        clients.foreach(x => x._2 ! Info(f"$username%s left the chat"))
      }
    }
 }

case class Connect(username: String)
case class Msg(msg: String)
case object Disconnect