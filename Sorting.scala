@main def m() =
  val listOfNums = List(4, 2, 1, 5, 3, 7)
  // sorted - Takes an implicit(given) Ordering(ascending order, by default)
  println(listOfNums.sorted)
  // sorted - Passing an Ordering object explicitly
  // Ordering.by creates an `Ordering` object
  println(listOfNums.sorted(Ordering.by(identity)))

  val alan = User("Alan", 43)
  val brittney = User("Brittney", 24)
  val turing = User("Turing", 30)
  val users = List(alan, brittney, turing)

  // default ordering of Users
  given userOrdering: Ordering[User] = Ordering.by(_.age)
  println(users.sorted)
  // explicit ordering of Users in descending order of ages
  println(users.sorted(Ordering.by((user: User) => user.age).reverse))

  // sortWith - easiest way
  println(users.sortWith(_.age < _.age))
  println(users.sortWith(_.age > _.age))

  val martin = Person("Martin", 43)
  val lex = Person("Lex", 24)
  val bill = Person("Bill", 30)
  val persons = List(martin, lex, bill)
  println(persons)

case class User(name: String, age: Int)
case class Person(name: String, age: Int) extends Ordered[Person]:
  // semantics of compare - return negative if this < that
  def compare(that: Person): Int =
    if (this.age == that.age) 0
    else if (this.age < that.age) 1
    else -1
  
