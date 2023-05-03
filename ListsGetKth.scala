@main def m() =
  val l = List(1, 34, 3, 23, 12, 85, 2)
  println(l.getKth(8))

extension [A](l: List[A])
  def getKth(n: Int): Option[A] = {
    @scala.annotation.tailrec
    def loop(i: Int, rem: List[A]): Option[A] = {
      if(rem.isEmpty) None
      else if (i == n) Some(rem.head)
      else loop(i + 1, rem.tail)
    }
    loop(1, l)
  }
