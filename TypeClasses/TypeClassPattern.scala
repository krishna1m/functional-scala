@main def m() =
  val jon = User("Jon", 23, "jon@rockthejvm.com")
  println(jon.toHTML)

case class User(name: String, age: Int, email: String)

implicit class HTMLEnrichment[T](value: T) {
  def toHTML(implicit serializer: HTMLSerializable[T]): String =
    serializer.serialize(value)
}

trait HTMLSerializable[T] {
  def serialize(value: T): String
}

implicit object UserSerializer extends HTMLSerializable[User] {
  def serialize(user: User): String =
    s"""|<div>
        |  <name>${user.name}</name>
        |  <age>${user.age}</age>
        |  <email>${user.email}</email>
        |</div>""".stripMargin
}
