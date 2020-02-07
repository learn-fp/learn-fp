package samples.ch02

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.{Await, Future}

class Ex3Test extends AnyFlatSpec with Matchers {
  "Semigroup[Future]" should "combine values inside futures" in {
    import Ex3._
    import SemigroupSyntax._
    import BasicSemigroupInstances._
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration._

    val future = Future.successful(12 -> 24) |+| Future.successful(23 -> 42)
    Await.result(future, 2.seconds) shouldBe (35, 66)

    List(1, 2, 3) |+| List(4, 5, 6) shouldBe (1 to 6).toList

  }
}
