@main def m() =
  val intVector = Vector(5, 6, 1, 4, 2, 7, 9, 10, 8, 3)
  val stringVector = Vector("abc", "dlksdjflk", "skjdfhiuweyr","a")
  given strOrdering: Ordering[String] = Ordering.fromLessThan[String](_.length > _.length)
  println(mergeSort(stringVector))

def merge[A](inputs: List[List[A]])(ordering: Ordering[Option[A]]): List[A] = {
  @scala.annotation.tailrec
  def rec(inputsRemaining: List[List[A]], result: List[A]): List[A] =
    inputsRemaining.filter(_.nonEmpty).sortBy(_.headOption)(ordering) match {
      case (smallest :: restA) :: restB =>
        rec(restA :: restB, smallest :: result)
      case _ =>
        result.reverse
    }
  rec(inputsRemaining = inputs, result = List.empty)
}

def iterate[T](iterations: Int, initial: T)(f: T => T): T =
  LazyList.iterate(initial)(f).take(iterations + 1).last

def mergeSort[A](input: Vector[A])(using ordering: Ordering[Option[A]]): Vector[A] = {
  val mergeWidth = 2
  iterate(
    iterations =
      if (input.isEmpty) 0
      else
        Math
          .ceil(Math.log(input.length.toDouble) / Math.log(mergeWidth.toDouble))
          .toInt,
    initial = input.iterator.map(i => i :: Nil)
  )(level =>
    level.grouped(mergeWidth).map(inputs => merge(inputs.toList)(ordering))
  ).flatten.toVector
}
