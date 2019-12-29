package samples.ch01

class EqualOps[T](t: T)(implicit equal: Equal[T]) {
  def ===(other: T): Boolean = equal.eq(t, other)

  def is(other: T): Boolean = equal.eq(t, other)
}

object EqualSyntax {
  implicit def toEqualOps[T](t: T)(implicit equal: Equal[T]): EqualOps[T] =
    new EqualOps(t)
}
