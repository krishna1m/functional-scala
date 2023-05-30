@main def m() =
  val l = List(1, 2, 3)
  val anotherList = Nil
  println(l.head)
  println(l.headOption)
  println(anotherList.headOption)

  val double: Int => Int = x => x * 2
  val doubled = l.map(double)
  println(doubled)

  val doubledEmpty = anotherList.map(double)
  println(doubledEmpty)

  def doubleHeadNaive(l: List[Int]): Int = l.head * 2

  def doubleHead(list: List[Int]): Option[Int] = list.headOption.map(x => x * 2) 
 
  // Option[A] -> Some[A], None

  println(doubleHead(l))
  println(doubleHead(anotherList))
