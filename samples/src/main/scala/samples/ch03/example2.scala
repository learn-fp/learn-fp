package samples.ch03

import samples.ch02.Semigroup
import samples.common.OptionHelper._

class OptionSemigroup[T: Semigroup] extends Semigroup[Option[T]] {
  import samples.ch02.SemigroupSyntax._
  override def combine(left: Option[T], right: Option[T]): Option[T] = {
    (left, right) match {
      case (Some(l), Some(r)) => (l |+| r).some
      case (l: Some[T], None) => l
      case (None, r: Some[T]) => r
      case (None, None)       => none[T]
    }
  }
}

class OptionMonoid[T: Monoid] extends OptionSemigroup[T] with Monoid[Option[T]]{
  override def zero: Option[T] = implicitly[Monoid[T]].zero.some
}

object OptionInstances {
  implicit def monoid[T: Monoid] = new OptionMonoid[T]
}

