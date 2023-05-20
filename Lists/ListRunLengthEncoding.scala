@main def m() =
  val list = List(1, 1, 2, 3, 4, 4, 4, 5, 5, 5, 5, 5, 9)
  println(list.rle)

extension [A](l: List[A])
  def rle: List[(A, Int)] = {
    @scala.annotation.tailrec
    def loop(rem: List[A], acc: List[(A, Int)]): List[(A, Int)] = {
      if(rem.isEmpty) acc.reverse
      else if (acc.isEmpty) loop(rem.tail, (rem.head, 1) +: acc)
      else if(rem.head == acc.head(0)) loop(rem.tail, (acc.head(0), acc.head(1) + 1) +: acc.tail)
      else loop(rem.tail, (rem.head, 1) +: acc)
    }
    loop(l, Nil)
  }
