@main def m() =
  val l1 = (1 to 100).toList
  val l2 = (56 to 100).toList
  println(l1.con(l2))

extension [A](l: List[A])
  // concat lists - l ++ anotherList
  def con(anotherList: List[A]): List[A] = {
    @scala.annotation.tailrec  
    def go(rem: List[A], acc: List[A]): List[A] = {
      if(rem.isEmpty) acc
      else go(rem.tail, rem.head :: acc)
    }
    go(l, anotherList)
  }
