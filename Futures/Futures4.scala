import scala.concurrent.{Future, Promise, Await}
import scala.concurrent.duration._
import scala.util.{Try, Success, Failure, Random}
// Execution Context handles thread allocation of futures
import scala.concurrent.ExecutionContext.Implicits.global

/*
 * Futures are immutable, "read-only" objects.
 * Promises are "writable-once" containers over a future.
 *
 * Thread 1:
 * - creates an empty promise
 * - knows how to handle the result
 *
 * Thread 2:
 * - holds the promise
 * - fulfills or fails the promise
 *   p.complete(Try {...})
 *   p.failure(new BadException(...))
 */
@main def m() =
  // Promises
  val promise = Promise[Int]() // promise - "controller over a future"
  val future = promise.future

  // thread 1 - "consumer"
  future.onComplete {
    case Success(r) => println(s"[consumer] I've received $r")
    case Failure(ex) => ex.printStackTrace()
  }

  // thread 2 - "producer"
  val producer = new Thread(() => {
    println("Crunching numbers...")
    Thread.sleep(3000)
    // "fulfilling the promise"
    promise.success(42) // promise.failure(cause: Throwable)
    println("[producer] Done")
  })

  producer.start()
  Thread.sleep(1000)
