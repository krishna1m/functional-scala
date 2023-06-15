@main def m() =
  val arr = Array(10, 4, 8, 3)
  println(arr.mkString("[", ", ", "]"))
  println(leftRightDifference(arr).mkString("[", ", ", "]"))

def leftRightDifference(nums: Array[Int]): Array[Int] = {
  val totalSum = nums.sum
  val answer = Array.fill(nums.length)(0)
  var leftSum = 0
  var rightSum = totalSum
  var currentNum = 0
  for(i <- 0 to nums.length - 1) {
    println(s"leftSum: $leftSum, rightSum: $rightSum, currentNum: $currentNum, nums(i): ${nums(i)}")
    leftSum = leftSum + currentNum
    rightSum = rightSum - nums(i)
    currentNum = nums(i)
    answer(i) = Math.abs(leftSum - rightSum)
  }
  answer
}
