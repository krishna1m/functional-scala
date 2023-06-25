/*
 * Self-types are a way to declare that a trait must be mixed into another trait, even though it doesn’t directly extend it. 
 * That makes the members of the dependency available without imports.
 */
trait Animal[A <: Animal[A]]{ self: A =>
  def breed: List[Animal[A]]
}

class Dog extends Animal[Dog] {
  override def breed: List[Animal[Dog]] = ???
}

class Cat extends Animal[Cat] {
  override def breed: List[Animal[Cat]] = ???
}
