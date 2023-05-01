def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
  val length = as.length
  def loop(i: Int): Boolean = {
    if(i == length - 1) true
    else if(!ordered(as(i), as(i + 1))) false
    else loop(i + 1)
  }
  loop(0)
}

@main def main() = {
  val as = Array(1, 2, 3, 33)
  println(isSorted(as, (x: Int, y: Int) => x < y))
}
