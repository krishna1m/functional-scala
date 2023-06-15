import scala.util.Random

def main(args: Array[String]): Unit =
  def number = Random.between(0.0, 1.0)
  def char = if(number > 0.99) '#' else '_'
  print("Enter row size: ")
  val row = scala.io.StdIn.readLine.toInt
  print("Enter col size: ")
  val col = scala.io.StdIn.readLine.toInt
  print("Enter iterations: ")
  val iterations = scala.io.StdIn.readLine.toInt
  //val row = 30
  //val col = 100
  val input = Array.fill(row, col)(char)
  // println(loopPop(0, input))
  // val iterations = 99
  loopCon(iterations, input)

def loopPop(i: Int, input: Array[Array[Char]]): Int = {
  if(allPounds(input)) i
  else {
    printMat(input)
    println()
    println()
    println()
    println()
    println()
    loopPop(i + 1, nextStatePop(input))
  }
}

def loopCon(iterations: Int, input: Array[Array[Char]]): Unit = {
  def go(i: Int, carry: Array[Array[Char]]): Unit = {
    if(i == iterations) ()
    else {
      println(s"Conway Run: $i")
      printMat(carry)
      println()
      println()
      println()
      println()
      println()
      go(i + 1, nextState(input))
    }
  }
  go(0, input)
}

def allPounds(arr: Array[Array[Char]]): Boolean =
  arr.forall { arrI =>
    arrI.forall(_ == '#')
  }

def printMat(input: Array[Array[Char]]): Unit =
  input.foreach { arr =>
    println(arr.mkString)
  }

def nextStatePop(curr: Array[Array[Char]]): Array[Array[Char]] = {
  val m = curr.size
  val n = curr(0).size
  val justPounds = for {
    i <- (0 to m - 1).toList
    j <- (0 to n - 1).toList
    if(curr(i)(j) == '#')
  } yield (i, j)

  justPounds.flatMap { case (i, j) =>
    List(
      (i , j + 1),
      (i , j - 1),
      (i + 1, j),
      (i - 1, j)
    ).filter { case (a, b) =>
      a >= 0 && a < m && b >= 0 && b < n
    }
  }.foreach { case (p, q) =>
      curr(p)(q) = '#'
  }
  curr
}

def nextState(curr: Array[Array[Char]]): Array[Array[Char]] = {
  val m = curr.size
  val n = curr(0).size
  val justPounds = for {
    i <- (0 to m - 1).toList
    j <- (0 to n - 1).toList
    if(curr(i)(j) == '#')
  } yield (i, j)

  justPounds.flatMap { case (i, j) =>
    curr(i)(j) = '_'
    List(
      (i , j + 1),
      (i , j - 1),
      (i + 1, j),
      (i - 1, j)
    ).filter { case (a, b) =>
      a >= 0 && a < m && b >= 0 && b < n
    }
  }.foreach { case (p, q) =>
      curr(p)(q) = '#'
  }
  curr
}

