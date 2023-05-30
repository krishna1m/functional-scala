@main def m() =
  val people = List(
      Person("Jon", 23),
      Person("Rita", 18),
      Person("Nick", 50)
    )
  import PersonAgeOrdering._ // does NOT import the givens in the object
  import PersonAgeOrdering.given // does import the givens in the object
  println(people.sorted)


case class Person(name: String, age: Int)
object Person { // this is checked for by itself (since it is the companion object of Person)
  given defaultOrderingByNameAsc: Ordering[Person] = Ordering.fromLessThan[Person]{ (a, b) =>
    a.name.compareTo(b.name) < 0
  }
}

object PersonAgeOrdering {
  given ageOrderingAsc: Ordering[Person] = Ordering.fromLessThan[Person](_.age < _.age)
}
