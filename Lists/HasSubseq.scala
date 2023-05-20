def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = {
  def traverse(checkedUptil: List[A], remSup: List[A], remSub: List[A]): Boolean = {
    println(s"checked uptil: $checkedUptil, remSup: $remSup, remSub: $remSub")
    if(remSub.isEmpty) true
    else if(remSup.isEmpty) false
    else if (remSup.head == remSub.head) traverse(checkedUptil, remSup.tail, remSub.tail)
    else traverse(checkedUptil.tail, checkedUptil.tail, sub)
  }
  traverse(sup, sup, sub)
}

@main def m() =
  val sup = (2 to 10).toList
  val sub = (3 to 5).toList
  println(hasSubsequence(sup, sub))
