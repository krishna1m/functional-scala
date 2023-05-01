@main def main() =
  println(init((1 to 10).toList))

def init[A](l: List[A]): List[A] = {
  def helper(rem: List[A], acc: List[A] = Nil): List[A] = {
    if(rem.isEmpty) acc.tail
    else helper(rem.tail, rem.head +: acc)
  }
  if(l.isEmpty) Nil
  else helper(l).reverse
}
