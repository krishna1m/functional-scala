/**
 * A magnet pattern is an alternative to method overload which might return different result
 * depending on input, where, instead of providing many method implementations, we provide one
 * argument which decides the result type.
 */
@main def m() =
  import AddMagnet._
  println(add1(1))
  println(add1("1"))

trait MathLib {
  def add1(x: Int): Int = x + 1
  def add1(s: String): Int = s.toInt + 1
  // more overloads
}

// "magnetize"
trait AddMagnet {
  def apply(): Int
}

object AddMagnet {
  def add1(magnet: AddMagnet): Int = magnet()
}

implicit class FromIntToMathMagnet(num: Int) extends AddMagnet {
  override def apply(): Int = num + 1
}

implicit class FromStringToMathMagnet(s: String) extends AddMagnet {
  override def apply(): Int = s.toInt + 1
}
