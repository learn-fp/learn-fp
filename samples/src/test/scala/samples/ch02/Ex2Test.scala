package samples.ch02

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Ex2Test extends AnyFlatSpec with Matchers{

  "Option Semigroup" should "combine two options" in {
    import BasicSemigroupInstances._
    import SemigroupSyntax._
    import Ex2._

    (10.2, 11.3).some |+| (12.3, 13.5).some shouldBe (22.5, 24.8).some

    10.some |+| 20.some shouldBe 200.some

    "Something".some |+| none[String] shouldBe none[String]
    none[String] |+| "Something".some shouldBe none[String]

    none[Int] |+| none[Int] shouldBe none[Int]

  }
}
