import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.util.{Try, Success, Failure, Random}
// Execution Context handles thread allocation of futures
import scala.concurrent.ExecutionContext.Implicits.global

case class User(name: String)
case class Transaction(sender: String, receiver: String, amount: Double, status: String)


object BankingApp {
  val name = "Rock the JVM banking"

  def fetchUser(name: String): Future[User] = Future {
    Thread.sleep(500)
    User(name)
  }

  def createTransaction(user: User, merchantName: String, amount: Double): Future[Transaction] = Future {
    Thread.sleep(1000)
    Transaction(user.name, merchantName, amount, "SUCCESS")
  }

  // returns the status of the transaction as a String
  def purchase(username: String, item: String, merchantName: String, cost: Double): String = {
    // fetch the user from the DB
    // create a transaction
    // wait for the transaction to finish
    val transactionStatusFuture = for {
      user <- fetchUser(username)
      transaction <- createTransaction(user, merchantName, cost)
    } yield transaction.status

    // to get the completed value inside a future
    Await.result(transactionStatusFuture, 2.seconds) // blocks until the Awaitable(here, future) is complete, if the duration passes, it throws an exception with a timeout

    // Result of Await.result on Future[String] is a String, not advisable as the Future could complete with an exception wrapped in a Failure.
    // Result of Await.ready on Future[String] is a Future[String], you call ".value" on the future to pattern match(Success/Failure)
  }
}
@main def m() =
  val status = BankingApp.purchase("Daniel", "iPhone 12", "rock the jvm store", 3000)
  println(status)

