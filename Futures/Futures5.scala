import scala.concurrent.{Future, Promise, Await}
import scala.concurrent.duration._
import scala.util.{Try, Success, Failure, Random}
// Execution Context handles thread allocation of futures
import scala.concurrent.ExecutionContext.Implicits.global


@main def m() =
  def inSequence[A, B](fa: Future[A], fb: Future[B]): Future[B] =
    for {
      _ <- fa
      b <- fb
    } yield b

  val aFuture = Future {Thread.sleep(1000); 2}
  val bFuture = Future {Thread.sleep(1000); "yo"}
  println(Await.result(inSequence(aFuture, bFuture), 3.seconds))

