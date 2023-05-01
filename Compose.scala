@main def main() =
  val g = (x: Int) => x.toString
  val f = (x: String) => (x * 2).toDouble
  println(compose(f, g)(2))

def compose[A, B, C](f: B => C, g: A => B): A => C = a => f(g(a))
