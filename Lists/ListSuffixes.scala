@main def m() =
  val list = (1 to 4).toList
  println(suffixes2(list))
/*
 suffixes([1, 2, 3, 4])
   = loop([4, 3, 2, 1], [[]])
   = loop([3, 2, 1], [[]])
 */

def suffixes[A](l: List[A]): List[List[A]] = {
  def loop(rem: List[A], acc: List[List[A]]): List[List[A]] = {
    if(rem.isEmpty) acc
    else if (acc.isEmpty) loop(rem.tail, List(rem.head) +: Nil +: acc)
    else loop(rem.tail, (rem.head +: acc.head) +: acc)
  }
  loop(l.reverse, List[List[A]]())
}

def suffixes2[A](l: List[A]): List[List[A]] = {
  def loop(rem: List[A], acc: List[List[A]]): List[List[A]] = {
    rem match {
      case Nil => acc
      case ::(remH, remT) => acc match {
        case Nil => loop(remT, List(remH) +: Nil +: acc)
        case ::(accH, accT) => loop(remT, (remH +: accH) +: acc)
      }
    }
  }
  loop(l.reverse, List[List[A]]())
}
