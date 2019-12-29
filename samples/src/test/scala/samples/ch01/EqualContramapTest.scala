package samples.ch01

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class EqualContramapTest extends FlatSpec with Matchers {

  "contramap" should "create a new equal instance" in {

    def contramap[T, Q](f: T => Q)(implicit equal: Equal[Q]): Equal[T] =
      new Equal[T] {
        def eq(left: T, right: T): Boolean = equal.eq(f(left), f(right))
      }

    import BasicEqualInstances.intEqual
    import EqualSyntax._

    implicit val personEqual = contramap[Person, Int](_.age)

    Person("Person1", 22) is Person("Person2", 22) shouldBe true
  }

  "scalaz" should "internally provide contramap" in {
    import scalaz._
    import Scalaz._

    implicit val personAgeEqual = implicitly[scalaz.Equal[Int]].contramap[Person](_.age)
  }
}

case class Person(name: String, age: Int)
