package samples.ch02

trait Semigroup[T] {
  def operation(left: T, right: T): T
}

object BasicSemigroupInstances {
  implicit val intMulSemigroup = new Semigroup[Int] {
    def operation(left: Int, right: Int): Int = left * right
  }

  implicit val stringSemigroup = new Semigroup[String] {
    def operation(left: String, right: String): String = left + right
  }

  type IntVector = (Int, Int)
  implicit val intVectorSemigroup = new Semigroup[IntVector] {
    def operation(left: IntVector, right: IntVector): IntVector =
      (left._1 + right._1, left._2 + right._2)
  }
}
