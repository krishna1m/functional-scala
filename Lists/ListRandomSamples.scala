@main def m() =
  val l = List(1, 3, 5, 62, 2, 2, 90)
  println(l.sample(3))
  println(l.sampleElegant(3))

extension [A](l: List[A])
  def sampleElegant(num: Int): List[A] = {
    lazy val length = l.length
    if(num < 0 | l.isEmpty) Nil
    else (1 to num)
            .toList
            .map(k => scala.util.Random.between(0, length))
            .map(idx => l(idx))
  }

  def sample(num: Int): List[A] = {
    lazy val length = l.length
    def rand: A = {
      val randomI = scala.util.Random.between(0, length)
      l(randomI)
    }

    @scala.annotation.tailrec
    def loop(i: Int, acc: List[A]): List[A] = {
      if(i == num) acc
      else loop(i + 1, rand +: acc)
    }
    if(num < 0 | l.isEmpty) Nil
    else loop(0, Nil)
  }
