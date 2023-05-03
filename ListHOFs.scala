extension [A](l: List[A])
  def mapNew[B](f: A => B): List[B] = {
    @scala.annotation.tailrec
    def loop(rem: List[A], acc: List[B]): List[B] = {
      if(rem.isEmpty) acc.reverse
      else loop(rem.tail, f(rem.head) +: acc)
    }
    loop(l, Nil)
  }

  def flatMapNew[B](f: A => List[B]): List[B] = {
    @scala.annotation.tailrec
    def loop(rem: List[A], acc: List[B]): List[B] = {
      if(rem.isEmpty) acc.reverse
      else loop(rem.tail, f(rem.head).reverse ++ acc)
    }
    loop(l, Nil)
  }

  def filterNew(predicate: A => Boolean): List[A] = {
    @scala.annotation.tailrec
    def loop(rem: List[A], acc: List[A]): List[A] = {
      if(rem.isEmpty) acc.reverse
      else if (predicate(rem.head)) loop(rem.tail, rem.head +: acc)
      else loop(rem.tail, acc)
    }
    loop(l, Nil)
  }

@main def m() =
  val list = List(1, 2, 3)
  println(list.mapNew(_ * 2))
  println(list.flatMapNew(x => List(x, x * 2)))
  println(list.filterNew(_ % 2 == 0))
