
package samples.ch01

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class OrderContramapTest extends FlatSpec with Matchers {
  
  "contramap" should "create new orders" in {
    def contramap[T,Q](f: T => Q)(implicit order: Order[Q]): Order[T] = new Order[T] {
      override def gt(left: T, right: T): Boolean = order.gt(f(left), f(right))
      override def gte(left: T, right: T): Boolean = order.gte(f(left), f(right))
      override def lt(left: T, right: T): Boolean = order.lt(f(left), f(right))
      override def lte(left: T, right: T): Boolean = order.lte(f(left), f(right))
      override def eq(left: T, right: T): Boolean = order.eq(f(left), f(right))
    }

    import OrderInstances._
    import OrderSyntax._

    implicit val personAgeOrder = contramap[Person, Int](_.age)

    Person("A", 22) gt Person("B", 21) shouldBe true
  }
}
