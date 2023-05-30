import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * A magnet pattern is an alternative to method overload which might return different result
 * depending on input, where, instead of providing many method implementations, we provide one
 * argument which decides the result type.
 * 
 * WARNING - Be extremely careful with using Magnet pattern with expressions/methods that are doing side effects
 */
@main def m() =
  import MessageMagnet._
  println(receive(new P2PRequest))
  println(receive(new P2PResponse))
  println(receive("yellow"))
  println(receive(42))
  println(receive(Future {
    new P2PRequest
  }))
  println(receive(Future {
    new P2PResponse
  }))

// 1. Define a Magnet Trait whose apply method return a type parametrized result type
sealed trait MessageMagnet[Result]:
  def apply(): Result

// 2. Define a method that takes a magnet and gives the result of the apply method for the magnet
object MessageMagnet {
  def receive[R](magnet: MessageMagnet[R]): R = magnet()
}

implicit class FromP2PRequest(request: P2PRequest) extends MessageMagnet[Int]:
  def apply(): Int = {
    println("handling P2P request")
    42
  }

implicit class FromP2PResponse(response: P2PResponse) extends MessageMagnet[String]:
  def apply(): String = {
    println("handling P2P response")
    "some string"
  }

implicit class FromString(string: String) extends MessageMagnet[String]:
  def apply(): String = {
    println("handling string request")
    string.reverse
  }

implicit class FromInt(num: Int) extends MessageMagnet[Int]:
  def apply(): Int = {
    println("handling int request")
    num % 5
  }

implicit class FromRequestFuture(future: Future[P2PRequest]) extends MessageMagnet[Int]:
  def apply(): Int = {
    println("handling future request")
    24
  }

implicit class FromResponseFuture(future: Future[P2PResponse]) extends MessageMagnet[Int]:
  def apply(): Int = {
    println("handling future response")
    42
  }

class P2PRequest
class P2PResponse



