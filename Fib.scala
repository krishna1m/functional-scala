@main def m() =
  println(fib(5))

def fib(n: Int): BigInt = {
  def loop(i: Int, nextToLast: BigInt = 0, last: BigInt = 1): BigInt = {
    if(i == n) nextToLast
    else loop(i + 1, last, nextToLast + last)
  }
  if(n == 1) 0
  else if(n == 2) 1
  else loop(1)
}
/*
  fib(5) 
    = loop(1, 0, 1)
    = loop(2, 1, 1)
    = loop(3, 1, 2)
    = loop(4, 2, 3)
    = loop(5, 3, 5)
    = 3
 * */
