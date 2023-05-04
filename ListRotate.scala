@main def m() =
  val list = (1 to 10).toList.rotate(12)
  println(list)

extension [A](l: List[A])
  def rotate(n: Int): List[A] = {
    val length = l.length
    @scala.annotation.tailrec
    def loop(i: Int, first: List[A], rem: List[A]): List[A] = {
      if(i == (n % length)) rem ::: first.reverse
      else loop(i + 1, rem.head +: first, rem.tail)
    }
    loop(0, Nil, l)
  }
