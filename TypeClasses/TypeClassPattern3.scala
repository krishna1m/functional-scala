@main def m() =
  val jon = User("Jon", 23, "jon@rockthejvm.com")
  println(jon.toHTML)

case class User(name: String, age: Int, email: String)

trait HTMLSerializable[T] {
  def serialize(value: T): String
}

object HTMLSerializable { // good to have - to surface out implicit type class
  def apply[T](using serializer: HTMLSerializable[T]) = 
    serializer
}

extension [T](value: T) {
  def toHTML(using serializer: HTMLSerializable[T]): String =
    serializer.serialize(value)
}


given userSerializer: HTMLSerializable[User] with {
  def serialize(user: User): String =
    s"""|<div>
        |  <name>${user.name}</name>
        |  <age>${user.age}</age>
        |  <email>${user.email}</email>
        |</div>""".stripMargin
}
