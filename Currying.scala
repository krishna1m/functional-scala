@main def m() =
  val f = (x: Int, y: Int) => x + y
  val g = (x: Int) => (y: Int) => x + y
  println(f(2, 3))
  println(curry(f)(2)(3))
  println(uncurry(g)(2, 3))

def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)

def uncurry[A, B, C](f: A => B => C): (A, B) => C =
  (a, b) => f(a)(b)
