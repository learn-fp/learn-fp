package samples.ch01

trait Equal[T] {
  def eq(left: T, right: T): Boolean
}

case class Person(name: String, age: Int)

object BasicEqualInstances {
  implicit val intEqual = new Equal[Int] {
    def eq(left: Int, right: Int): Boolean = left == right
  }
  implicit val stringEqual = new Equal[String] {
    def eq(left: String, right: String): Boolean = left == right
  }
  implicit val personEqual = new Equal[Person] {
    def eq(left: Person, right: Person): Boolean = left == right
  }

}

