@main def m() =
  println(List(3, 5, 1, 2, 1, 3, 5, 7).sortN)
  println(("key", 1).stringSerialize)
  val jon = Person("Jon", 23)
  println(PersonSerializer.serialize(jon))
  println(JSONSerializable[Person].serialize(jon))
  println(implicitly[JSONSerializable[Person]].serialize(jon))
//  println(JSONSerializable.serialize(jon)) - does not work

// implicit val/object is the same as a "given"
// implicit parameters are the same as the "using" clause
// implicit class is the same as an extension method without a name for the class
implicit class ListSorting[A](l: List[A]) {
  def sortN(using ordering: Ordering[A]): List[A] = l.sorted(ordering)
}


extension [K, V](tuple: Tuple2[K, V])
  def stringSerialize: String = 
    s"""|{
        | "${tuple._1}": "${tuple._2}"
        |}""".stripMargin

case class Person(name: String, age: Int)

trait JSONSerializable[T]:
  def serialize(value: T): String

object JSONSerializable {
  def apply[T](using serializer: JSONSerializable[T]): JSONSerializable[T] =
    serializer
}

implicit object PersonSerializer extends JSONSerializable[Person]{
  def serialize(person: Person): String =
    s"""|{
        | "name": "${person.name}"
        | "age": "${person.age}"
        |}""".stripMargin
}

