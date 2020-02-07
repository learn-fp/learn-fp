package samples.ch02

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Ex4Test extends AnyFlatSpec with Matchers {
  import BasicSemigroupInstances._
  import Ex4.reduce
  import samples.common.OptionHelper._

  "reduce" should "return None if the list is empty" in {
    reduce(List[Int]()) shouldBe none[Int]
  }

  it should "return the head if the list has just one element" in {
    reduce(List("Test")) shouldBe "Test".some
    reduce(List(12)) shouldBe 12.some
  }

  it should "sum all elements in a long list" in {
    val term = "Hello world!"
    val list = term.toList.map(_.toString)
    reduce(list) shouldBe term.some
    // Note that the Semigroup[Int] that we've imported here multiplies numbers
    reduce(1 :: 2 :: 3 :: 4 :: 5 :: Nil) shouldBe 120.some
  }
}
