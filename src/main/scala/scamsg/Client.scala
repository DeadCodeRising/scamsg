package scamsg

import akka.actor._

class Client(val username: String, server: ActorRef) extends Actor {
  
  server ! Connect(username)
  
  def receive = {
    case NewMsg(from, msg) => {
      println(f"[$username%s's client] - $from%s: $msg%s")
    }
    case Msg(msg) => server ! Msg(msg)
    case Info(msg) => {
      println(f"[$username%s's client] - $msg%s")
    }
    case Disconnect => {
      server ! Disconnect
      exit
    }
  }
 
}

case class Info(msg: String)
case class NewMsg(from: String, msg: String)
