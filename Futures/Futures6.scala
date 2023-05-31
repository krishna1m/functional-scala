import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


@main def m() =
  val listOfFutures: List[Future[Int]] = 
    List(
      Future(1),
      Future(2),
      Future(3)
    )
  val futureOfList: Future[List[Int]] = Future.sequence(listOfFutures)

  val transformed: Future[List[Int]] = Future.traverse(listOfFutures){ (futureInt: Future[Int]) =>
    futureInt.map(_ * 2)
  }

  println(futureOfList.value)
  println(transformed.value)
