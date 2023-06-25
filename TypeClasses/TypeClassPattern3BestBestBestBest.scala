@main def m() =
  // Type classes are an example of doing Ad-hoc polymorphism
  val jon = User("Jon", 23, "jon@rockthejvm.com")
  println(jon.toHTML)
  println(toHtmlBoilerPlate(jon))

case class User(name: String, age: Int, email: String)

// 1. Type class and its companion object
trait HTMLSerializable[T]:
  def serialize(value: T): String

object HTMLSerializable:  // good to have - to surface out implicit type class
  // use of context bounds, [T: HTMLSerializable]
  def apply[T: HTMLSerializable]: HTMLSerializable[T] = 
    summon[HTMLSerializable[T]] // `summon` instead of `implicitly`

// context function syntax - unnecessary here :p
type WithHTMLSerialiableString[T] = HTMLSerializable[T] ?=> String
// 2. Type class enrichment
extension [T](value: T)
  def toHTML: WithHTMLSerialiableString[T] =
    HTMLSerializable[T].serialize(value) // calls the apply method from the companion object which in turns calls `implicitly` to surface out the implicit parameter


// 3. Type class instance - givens can be anonymous or with a name
given HTMLSerializable[User] with
  def serialize(user: User): String =
    s"""|<div>
        |  <name>${user.name}</name>
        |  <age>${user.age}</age>
        |  <email>${user.email}</email>
        |</div>""".stripMargin

// 4. Context Bounds - if needed
def toHtmlBoilerPlate[T: HTMLSerializable](value: T): String =
  s"<html><body>${value.toHTML}</html></body>"
