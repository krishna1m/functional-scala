case class Person(val name: String, var age: Int = 0){
  def this() = this("DefaultJohn", 20)
}

@main def m() =
  // constructor, getter - name and age, setter - age
  val jon = Person("Jon", 24)
  val babyJohn = Person("John")
  val defaultJohn = new Person()


  println(jon.toString)
  println(babyJohn)
  println(defaultJohn)
