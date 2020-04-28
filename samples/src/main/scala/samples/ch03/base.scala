package samples.ch03

import samples.ch02.Semigroup

trait Monoid[T] extends Semigroup[T] {
  def zero: T
}

object BaseMonoidInstances {
  implicit val intMonoid : Monoid[Int] = new Monoid[Int] {
    override def zero: Int = 0
    override def combine(left: Int, right: Int): Int = left + right
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    override def zero: String = ""
    override def combine(left: String, right: String): String = left + right
  }
}
