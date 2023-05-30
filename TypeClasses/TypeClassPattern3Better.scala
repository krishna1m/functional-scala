@main def m() =
  // Type classes are an example of doing Ad-hoc polymorphism
  val jon = User("Jon", 23, "jon@rockthejvm.com")
  println(jon.toHTML)
  println(toHtmlBoilerplate(jon))

case class User(name: String, age: Int, email: String)

// 1. Type class Instance and its companion object
trait HTMLSerializable[T]:
  def serialize(value: T): String

object HTMLSerializable:  // good to have - to surface out implicit type class
  def apply[T](using serializer: HTMLSerializable[T]): HTMLSerializable[T] = 
    serializer


// 2. Type class enrichment
extension [T](value: T)
  def toHTML(using serializer: HTMLSerializable[T]): String =
    HTMLSerializable[T].serialize(value)


// 3. Type class instance
given userSerializer: HTMLSerializable[User] with
  def serialize(user: User): String =
    s"""|<div>
        |  <name>${user.name}</name>
        |  <age>${user.age}</age>
        |  <email>${user.email}</email>
        |</div>""".stripMargin
        
// 4. Context Bounds - if needed
def toHtmlBoilerplate[T](value: T)(using serializer: HTMLSerializable[T]): String =
  s"<html><body>${value.toHTML}</html></body>"
