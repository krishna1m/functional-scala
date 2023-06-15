import scala.util.{Try, Success, Failure}

@main def m() =
  println(divide(4, 2))
  println(divideTryCatch(4, 0))
  println(divideF(4, 0))
  val list = List(3, 4, 0, 4, 0, 1, 5)
  val result = list.map(x => Try(5 / x)).map {
    case Success(value) => value * 2
    case Failure(_) => 0
  }
  println(result)



def divide(a: Int, b: Int): Int = a / b

def divideF(a: Int, b: Int): Try[Int] = Try(a / b)

def divideTryCatch(a: Int, b: Int): Int = 
  try {
    a / b
  } catch {
    case e: Exception => 0
  }
