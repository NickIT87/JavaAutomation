import scala.io.StdIn

object HelloWorldScalaApp extends App {
  val name = StdIn.readLine("Hi! Enter your name: ")
  println("Hello " + name + "!")
}