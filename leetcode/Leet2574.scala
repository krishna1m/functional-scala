@main def m() =
  val arr = Array(10, 4, 8, 3)
  println(arr.mkString("[", ", ", "]"))
  println(cumulativeSum(arr).mkString("[", ", ", "]"))
  println(cumulativeSumRev(arr).mkString("[", ", ", "]"))
  println(leftRightDifference(arr).mkString("[", ", ", "]"))

def cumulativeSum(xs: Array[Int]): Array[Int] = {
  val result = Array.fill(xs.length)(0)
  val length = result.length - 1
  def loop(i: Int, acc: Int): Unit = {
    if(i > length) ()
    else {
      result(i) = acc + xs(i)
      loop(i + 1, result(i))
    }
  }
  loop(0, 0)
  result
}

def cumulativeSumRev(xs: Array[Int]): Array[Int] = {
  val result = Array.fill(xs.length)(0)
  val length = result.length - 1
  def loop(i: Int, acc: Int): Unit = {
    if(i < 0) ()
    else {
      result(i) = acc + xs(i)
      loop(i - 1, result(i))
    }
  }
  loop(result.length - 1, 0)
  result
}

def mod(num: Int): Int = {
  if(num < 0) -num
  else num
}

def leftRightDifference(nums: Array[Int]): Array[Int] = {
  val answer = Array.fill(nums.length)(0)
  cumulativeSum(nums).zip(cumulativeSumRev(nums)).map((num1, num2) => mod(num1 - num2))
}
