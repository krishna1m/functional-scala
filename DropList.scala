@main def m() =
  val list = (1 to 10).toList
  println(drop(list, 5))
  println(dropWhile(list, (x: Int) => x < 6))

def drop[A](l: List[A], n: Int): List[A] = {
  def loop(rem: List[A], i: Int): List[A] = {
    if(rem.isEmpty) Nil
    else if (i == 0) rem
    else loop(rem.tail, i - 1)
  }
  loop(l, n)
}

def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
  def loop(rem: List[A]): List[A] = {
    if (rem.isEmpty) Nil
    else if (f(rem.head)) loop(rem.tail)
    else rem
  }
  loop(l)
}
