@main def m() =
  val list = (1 to 100).toList
  println(list.rev)

extension [A](l: List[A])
  def rev: List[A] = {
    @scala.annotation.tailrec
    def go(rem: List[A], acc: List[A]): List[A] = {
      if(rem.isEmpty) acc
      else go(rem.tail, rem.head :: acc)
    }
    go(l, Nil)
  }
