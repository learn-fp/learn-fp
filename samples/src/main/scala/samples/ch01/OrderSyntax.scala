package samples.ch01

// Exercise 1.4

class OrderOps[T](t: T)(implicit order: Order[T]) {
  def <(other: T): Boolean = order.lt(t, other)
  def >(other: T): Boolean = order.gt(t, other)
  def <=(other: T): Boolean = order.lte(t, other)
  def >=(other: T): Boolean = order.gte(t, other)
  def lt(other: T): Boolean = order.lt(t, other)
  def lte(other: T): Boolean = order.lte(t, other)
  def gt(other: T): Boolean = order.gt(t, other)
  def gte(other: T): Boolean = order.gte(t, other)
  def ===(other: T): Boolean = order.eq(t, other)
}
object OrderSyntax {
  implicit def toOrderOps[T](t: T)(implicit order: Order[T]) = new OrderOps(t)
}
