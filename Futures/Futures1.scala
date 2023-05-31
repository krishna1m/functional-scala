import scala.concurrent.Future
import scala.util.{Try, Success, Failure}
// Execution Context handles thread allocation of futures
import scala.concurrent.ExecutionContext.Implicits.global

@main def m() =
  val aFuture: Future[Int] = Future {
    Thread.sleep(2)
    42
  }
  println(aFuture.value) // Option[Try[Int]] - Try to account for the fact that the computation might have failed, Option to account for the fact that the computation might still not have been computed
  Thread.sleep(3)
  aFuture.onComplete { (t: Try[Int]) => t match {
      case Success(meaningOfLife) => println(s"The meaning of life is $meaningOfLife")
      case Failure(e) =>  println(s"I have failed with $e")
    }
  }

  // partial function syntax
  aFuture.onComplete { 
    case Success(meaningOfLife) => println(s"The meaning of life is $meaningOfLife")
    case Failure(e) =>  println(s"I have failed with $e")
  }
  aFuture.foreach(println)
