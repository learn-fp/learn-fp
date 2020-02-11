package samples.ch02

trait Semigroup[T] {
  def combine(left: T, right: T): T
}

object BasicSemigroupInstances {
  implicit val intMulSemigroup: Semigroup[Int] = new Semigroup[Int] {
    def combine(left: Int, right: Int): Int = left * right
  }

  implicit val stringSemigroup: Semigroup[String] = new Semigroup[String] {
    def combine(left: String, right: String): String = left + right
  }

  implicit val doublePlusSemigroup: Semigroup[Double] = new Semigroup[Double] {
    override def combine(left: Double, right: Double): Double = left + right
  }

  type IntVector = (Int, Int)
  implicit val intVectorSemigroup: Semigroup[(Int, Int)] = new Semigroup[IntVector] {
    def combine(left: IntVector, right: IntVector): IntVector =
      (left._1 + right._1, left._2 + right._2)
  }

  import SemigroupSyntax._
  type Vec[T] = (T, T)
  implicit def vectorSemigroup[T: Semigroup]: Semigroup[Vec[T]] = new Semigroup[Vec[T]] {
    override def combine(left: (T, T), right: (T, T)): (T, T) =
      (left._1 |+| right._1 , left._2 |+| right._2)
  }
}
