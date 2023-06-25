trait Semigroup[T]:
  def combine(a: T, b: T): T

case class Expense(name: String, price: Int)
object Expense:
  given Ordering[Expense] = Ordering.fromLessThan[Expense](_.price < _.price)
  given Semigroup[Expense] with
    def combine(a: Expense, b: Expense): Expense = Expense(s"${a.name}, ${b.name}",a.price + b.price)
end Expense

extension [A: Semigroup : Ordering](list: List[A])
  def sortN: List[A] = list.sorted
  def combineAll: A = 
    list.reduceLeft(summon[Semigroup[A]].combine)

@main def m() = 
  val expenses = List(
      Expense("macbook", 3150),
      Expense("watch", 100),
      Expense("airpods", 1000)
    )
  println(expenses.sortN)
  println(expenses.combineAll)
