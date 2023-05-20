@main def m() =
  val vec = Vector(5, 6, 1, 4, 2, 7, 9, 10, 8, 3)
  println(mergeSort(vec))

def merge(inputs: List[List[Int]]): List[Int] = {
  @scala.annotation.tailrec
  def rec(inputsRemaining: List[List[Int]], result: List[Int]): List[Int] =
    inputsRemaining.filter(_.nonEmpty).sortBy(_.headOption) match {
      case (smallest :: restA) :: restB =>
        rec(restA :: restB, smallest :: result)
      case _ =>
        result.reverse
    }
  rec(inputsRemaining = inputs, result = List.empty)
}

def iterate[T](iterations: Int, initial: T)(f: T => T): T =
  LazyList.iterate(initial)(f).take(iterations + 1).last

def mergeSort(input: Vector[Int]): Vector[Int] = {
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
    level.grouped(mergeWidth).map(inputs => merge(inputs.toList))
  ).flatten.toVector
}
