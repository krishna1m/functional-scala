trait Animal[A <: Animal[A]]{ self: A =>
  def breed: List[Animal[A]]
}

class Dog extends Animal[Dog] {
  override def breed: List[Animal[Dog]] = ???
}

class Cat extends Animal[Cat] {
  override def breed: List[Animal[Cat]] = ???
}
