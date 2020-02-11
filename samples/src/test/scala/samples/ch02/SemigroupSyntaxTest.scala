package samples.ch02

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
class SemigroupSyntaxTest extends AnyFlatSpec with Matchers {

  "SemigroupSyntax" should "provide an easy way to combine values" in {
    import SemigroupSyntax._
    import BasicSemigroupInstances._

    2 |+| 3 shouldBe 6
    "Hello" |+| " " |+| "World!" shouldBe "Hello World!"
    (1, 1) |+| (2, 3) shouldBe (3, 4)
  }
}
