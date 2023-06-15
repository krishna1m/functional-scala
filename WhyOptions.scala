@main def m() =
  val l: List[Int] = List(1, 2, 3)
  val anotherList = Nil
  println(l.head)
  println(l.headOption) // Some(1)
  println(anotherList.headOption) // None

  val double: Int => Int = x => x * 2
  val listDoubled = l.map(double)
  println(listDoubled) // List(2, 4, 6)

  val doubledEmpty = anotherList.map(double)
  println(doubledEmpty) // None

  def doubleHeadNaive(l: List[Int]): Int = l.head * 2 // for empty list, it will fail

  def doubleHead(list: List[Int]): Option[Int] = list.headOption.map(x => x * 2) 
 
  // Option[A] -> Some[A], None

  println(doubleHead(l))
  println(doubleHead(anotherList))
