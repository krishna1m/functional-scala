// given polynomial p(x), with degree n, we have (n + 1) coefficients as a list
// given a number n, find p(n)
def horner(degree: Int, cs: List[Double], n: Double): Double = {
  def go(left: Int, rem: List[Double], acc: Double): Double = {
    println(s"left: ${left}, remlist: ${rem}, acc: ${acc}")
    if((left == 0 && rem.isEmpty) == true) acc
    else if(xor(left == 0, rem.isEmpty)) throw new Exception("bad input")
    else go(left - 1, rem.tail, acc * n + rem.head)
  }
  go(degree + 1, cs, 0.0)
}

def xor(p1: Boolean, p2: Boolean) = (p1 && !p2) || (!p1 && p2)

@main
def m() = {
  val coefficients = 7 :: 6 :: 8 :: 13 :: 2 :: 2 :: 3 :: 9 :: 5 :: 10 :: Nil
  val degree = 9
  val number = 2
  println(horner(degree, coefficients.map(_.toDouble), number.toDouble))
}
