/* List(2,3,5,6,8,2) and k = 10
Return the count of sub arrays where sum of elements in an sub array is equal to given sum */
@main def m() =
  val list = List(2,3,5,6,8,2)
  println(cSubArr(list,10))

def cSubArr(l: List[Int], k: Int): Int =
  @scala.annotation.tailrec
  def loop(rem: List[Int], buffer: List[Int], count: Int): Int =
    println(s"rem: $rem, buffer: $buffer, count: $count")
    if(rem.isEmpty) {
      if(buffer.sum == k) count + 1
      else count
    }
    else if(buffer.sum == k) loop(rem.tail, buffer :+ rem.head, count + 1)
    else if(buffer.sum > k) loop(rem, buffer.tail, count)
    else loop(rem.tail, buffer :+ rem.head, count)
  loop(l, Nil, 0)
