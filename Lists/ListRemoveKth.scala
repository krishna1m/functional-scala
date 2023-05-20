@main def m() =
  val list = (1 to 43).toList
  println(list.remKth(4))

extension [A](l: List[A])
  def remKth(k: Int): List[A] = {
    @scala.annotation.tailrec
    def loop(i: Int, accStart: List[A], remEnd: List[A]): List[A] = {
      if(remEnd.isEmpty) l
      else if(i == k) accStart.reverse ++ remEnd.tail
      else loop(i + 1, remEnd.head :: accStart , remEnd.tail)
    }
    loop(1, Nil, l)
  }
