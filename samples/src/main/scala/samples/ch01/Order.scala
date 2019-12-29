package samples.ch01

trait Order[T] extends Equal[T] {
  def gt(left: T, right: T): Boolean
  def gte(left: T, right: T): Boolean
  def lt(left: T, right: T): Boolean
  def lte(left: T, right: T): Boolean
}

object OrderInstances {
  implicit val intOrder = new Order[Int] {
    override def gt(left: Int, right: Int): Boolean = left > right
    override def gte(left: Int, right: Int): Boolean = left >= right
    override def lt(left: Int, right: Int): Boolean = left < right
    override def lte(left: Int, right: Int): Boolean = left <= right
    override def eq(left: Int, right: Int): Boolean = left == right
  }
}

