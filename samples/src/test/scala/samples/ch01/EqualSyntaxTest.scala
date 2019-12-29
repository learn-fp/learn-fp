package samples.ch01

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class EqualSyntaxTest extends FlatSpec with Matchers {

  "EqualSyntax" should "Work like direct Equal typeclass usage" in {
    import BasicEqualInstances._

    12 === 12 shouldBe true
    12 === 13 shouldBe false
    Person("p1", 28) === Person("p2", 28) shouldBe false
    Person("p1", 28) === Person("p1", 28) shouldBe true
  }
}
