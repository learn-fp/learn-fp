package samples.ch02

object Ex2 {
  import SemigroupSyntax._

  implicit def optionSemigroup[T: Semigroup]: Semigroup[Option[T]] =
    new Semigroup[Option[T]] {
      override def combine(left: Option[T], right: Option[T]): Option[T] =
        for {
          l <- left
          r <- right
        } yield l |+| r
    }

  implicit class OptionHelper[T](value: T) {
    def some: Option[T] = Some(value)

  }
  def none[T]: Option[T] = None
}
