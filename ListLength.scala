@main def m() =
  val list = (2 to 11).toList
  println(list.len)

extension [A](l: List[A])
  def len: Int = {
    @scala.annotation.tailrec
    def loop(i: Int, rem: List[A]): Int = {
      if(rem.isEmpty) i
      else loop(i + 1, rem.tail)
    }
    loop(0, l)
  }
