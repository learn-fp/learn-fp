package samples.ch03

import samples.common.OptionHelper._

object Example1 {
  def optionMonoid[T: Monoid]: Monoid[Option[T]] = new Monoid[Option[T]] {
    import samples.ch02.SemigroupSyntax._

    override def zero: Option[T] = implicitly[Monoid[T]].zero.some
    override def combine(left: Option[T], right: Option[T]): Option[T] = {
      (left, right) match {
        case (Some(l), Some(r)) => (l |+| r).some
        case (l: Some[T], None) => l
        case (None, r: Some[T]) => r
        case (None, None)       => none[T]
      }
    }
  }
}
