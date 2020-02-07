package samples.ch02

import scala.concurrent.{ExecutionContext, Future}

object Ex3 {

  import SemigroupSyntax._
  implicit def listSemigroup[T]: Semigroup[List[T]] = (l, r) => l ::: r

  implicit def futureSemigroup[T: Semigroup](
      implicit ec: ExecutionContext
  ): Semigroup[Future[T]] =
    (left, right) =>
      for {
        l <- left
        r <- right
      } yield l |+| r
}
