package samples.ch01

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ScalazTest extends FlatSpec with Matchers {
  "Scalaz" should "Support Equal and Order" in {
    import scalaz._
    import Scalaz._

    1 =/= 2 shouldBe true
    1 lte 2 shouldBe true

    List(1, 2) <= List(3) shouldBe true

    12.some > none[Int] shouldBe true

  }
}
