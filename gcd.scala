def gcd(a: Int, b: Int): Int = {
  def go(x: Int, y: Int): Int = {
    println(s"x: $x, y: $y")
    if(x % y == 0) y
    else go(y, x % y)
  }
  if(a > b) go(a, b) else go(b, a)
}

@main
def main = {
  val a = 258
  val b = 60
  println(s"The gcd of $a and $b is: ${gcd(a, b)}")
}
