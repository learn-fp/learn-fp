package samples.ch00

object Ex2 {
  implicit class BooleanWrapper(b: Boolean) {
    def ?[T](value: T): Option[T] = if (b) Some(value) else None
  }

  implicit class OptionWrapper[T](o: Option[T]) {
     def |(other: T) : T = o.getOrElse(other)
  }

  val b: Boolean = ??? // some boolean value

  b ? "this" | "that"

  // or

  (1 == 2) ? "this" | "that"
}
