@main def m() =
  val list = List(1, 3, 4)
  println(list.dupEach(3))
  println(list.dupEach2(3))
  println(list.dupEach3(3))

def listFill[A](n: Int)(a: A): List[A] = {
  @scala.annotation.tailrec
  def loop(i: Int, acc: List[A]): List[A] = {
    if(i == n) acc
    else loop(i + 1, a +: acc)
  }
  loop(0, Nil)
}

extension [A](l: List[A])

  def dupEach(n: Int): List[A] = {
    @scala.annotation.tailrec
    def loop(rem: List[A], acc: List[A]): List[A] = {
      if(rem.isEmpty) acc.reverse
      else loop(rem.tail, List.fill(n)(rem.head) ::: acc)
    }
    loop(l, Nil)
  }

  def dupEach2(n: Int): List[A] = l.flatMap(i => List.fill(n)(i))
  def dupEach3(n: Int): List[A] = l.flatMap(i => listFill(n)(i))
