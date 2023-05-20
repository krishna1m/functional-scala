extension [A](l: List[A])
  def flatMapN[B](f: A => List[B]): List[B] = {
    @scala.annotation.tailrec
    def loop(rem: List[A], current: List[B], acc: List[B]): List[B] = {
      (rem, current) match {
        case (Nil, Nil) => acc
        case (head :: tail, Nil) => loop(tail, f(head), acc)
        case (_, head :: tail) => loop(rem, tail, head +: acc)
      }
    }
    loop(l, Nil, Nil).reverse
  }

@main def m() =
  val list = (1 to 10000).toList.flatMapN(n => List(n, n * 2))
  println(list)
