package scamsg

import scala.actors._
import scala.actors.Actor._

class Server extends Actor {
  
  def act() {
    loop {
      react {
        case Connect => println("Hey")
        case _ => println("Hey no")
      }
    }
  }
}

abstract class Cmd

case class Connect() extends Cmd 