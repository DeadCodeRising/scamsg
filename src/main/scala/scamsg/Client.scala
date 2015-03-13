package scamsg

import scala.actors._
import scala.actors.Actor._

class Client(val username: String, server: Actor) extends Actor {
  
  def act() {
    
	server ! Connect(username)
    
    loop {
      react {
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
  }
}

case class Info(msg: String)
case class NewMsg(from: String, msg: String)
