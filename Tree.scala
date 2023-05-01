sealed trait Tree[+A]:
  def map[B](f: A => B): Tree[B]
end Tree

case class Leaf[A](value: A) extends Tree[A]:
  override def map[B](f: A => B): Tree[B] = Leaf(f(value))
end Leaf

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]:
  override def map[B](f: A => B): Tree[B] = Branch(left.map(f), right.map(f))
end Branch

def fold[A, B](t: Tree[A])(f: (B, A) => B)(start: B): B = {
  def helper(st: Tree[A], acc: B = start): B = {
    st match
      case Leaf(value) => f(acc, value)
      case Branch(l, r) => {
        val interim = helper(l, acc)
        helper(r, interim)
      }
  }
  helper(t)
}

def size[A](t: Tree[A]): Int = {
  def helper(st: Tree[A]): Int = {
    st match
      case Leaf(_) => 1
      case Branch(l, r) => helper(l) + helper(r) + 2
  }
  helper(t)
}

def maximum(t: Tree[Int]): Int = {
  def helper(st: Tree[Int], maxU: Int): Int = {
    st match
      case Leaf(value) => value.max(maxU)
      case Branch(l, r) => {
        val maxLeft = helper(l, maxU)
        helper(r, maxLeft)
      }
  }
  helper(t, Int.MinValue)
}

def depth(t: Tree[Int]): Int = {
  def helper(st: Tree[Int], currDepth: Int): Int = {
    st match
      case Leaf(_) => currDepth
      case Branch(l, r) => {
        helper(l, currDepth + 1).max(helper(r, currDepth + 1))
      }
  }
  helper(t, 0)
}

@main def m() =
  val intTree = Branch(Branch(Leaf(1), Leaf(20)), Leaf(10))
  println(fold(intTree)((x: Int, y: Int) => x + y)(0))
