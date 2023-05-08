import scala.math.Ordering

@main def m() =
  val list = (3, 4, 1, 1, 23, 235).toList
  println(list.isort(Ordering.by(identity)))
  println(list.isort(Ordering.fromLessThan[Int](_ < _)))

extension [A](l: List[A])
  def isort(ordering: Ordering[A]): List[A] = {
    @scala.annotation.tailrec
    def loop(rem: List[A], acc: List[A]): List[A] = {
      if(rem.isEmpty) acc
      else loop(rem.tail, insert(rem.head, Nil, acc))
    }

    @scala.annotation.tailrec
    def insert(a: A, before: List[A], after: List[A]): List[A] = {
      if(after.isEmpty || ordering.lteq(a, after.head)) before.reverse ::: a :: after
      else insert(a, after.head :: before, after.tail)
    }
    loop(l, Nil)
  }
