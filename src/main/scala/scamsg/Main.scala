package scamsg

import akka.actor._
import akka.actor

object Main extends App {
  
  override def main(args: Array[String]) {
    val system = ActorSystem("System")
    
    val server = system.actorOf(Props[Server])
    
    val c1 = system.actorOf(Props(new Client("Sam", server)))
    c1 ! Msg("Hi, anyone here?")
    
    val c2 = system.actorOf(Props(new Client("Mia", server)))
    val c3 = system.actorOf(Props(new Client("Luke", server)))
    
    c2 ! Msg("Hello")
    
    c3 ! Disconnect
    
  }

}