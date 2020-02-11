package samples.ch02

class SemigroupOps[T: Semigroup](left: T){
  def |+|(right: T): T = implicitly[Semigroup[T]].combine(left, right)
}

object SemigroupSyntax {
  implicit def toOps[T: Semigroup](left: T): SemigroupOps[T] = new SemigroupOps[T](left)
}

