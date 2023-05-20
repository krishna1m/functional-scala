@main def m() =
  val l1 = (1 to 10).toList
  val l2 = (21 to 30).toList
  val l3 = (31 to 50).toList
  println(concat(List(l1, l2, l3)))
  

def concat[A](ls: List[List[A]]): List[A] = {
  def concatTwo[A](l1: List[A], l2: List[A]): List[A] = {
    def loop(rem: List[A], acc: List[A]): List[A] = {
      if(rem.isEmpty) acc
      else loop(rem.tail, rem.head +: acc)
    }
    loop(l1, l2)
  }
  def go(rem: List[List[A]], acc: List[A]): List[A] = {
    if(rem.isEmpty) acc
    else go(rem.tail, concatTwo(rem.head, acc))
  }
  go(ls, Nil).reverse
}

