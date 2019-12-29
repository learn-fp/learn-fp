package samples.ch01

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import BasicEqualInstances.personEqual

class EqualSimpleTest extends FlatSpec with Matchers {

  def peopleAreEqual(p1: Person, p2: Person): Boolean = {
    personEqual.eq(p1, p2)
  }

  def areEqual[T](left: T, right: T)(implicit equal: Equal[T]): Boolean = {
    equal.eq(left, right)
  }

  "both helper functions" should "work with direct TypeClass usage" in {
    val p1 = Person("P1", 32)
    val p2 = Person("P2", 44)

    peopleAreEqual(p1, p2) shouldBe false
    peopleAreEqual(p2, p2) shouldBe true
    peopleAreEqual(p1, p1) shouldBe true

    areEqual(p1, p2) shouldBe false
    areEqual(p2, p2) shouldBe true
    areEqual(p1, p1) shouldBe true
  }
}
