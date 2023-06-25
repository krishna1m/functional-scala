given String = "4"
given Int = 5
def add(num: Int)(using string: String, number: Int): Int =
  val stringN = string.toInt
  num + stringN + number

@main def m() =
  println(add(1))
