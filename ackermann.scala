def ackermann(m: Int, n: Int): Int = {
  if(m == 0) n + 1
  else if(m != 0 && n == 0) ackermann(m - 1, 1)
  else ackermann(m - 1, ackermann(m, n - 1))
}

@main
def main = {
  val m = 1
  val n = 3
  println(s"A($m, $n) = ${ackermann(m, n)}")
}
