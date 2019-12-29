package samples.ch00

object Ex1 {
    class OptionWrapper[T](t: T) {
        def some: Option[T] = Some(t)
    }
    implicit def valueToOptionWrapper[T](t: T): OptionWrapper[T] = new OptionWrapper(t)

    println(2.some)
    println("Hi!".some)
    val o : Option[Double] = 2.2.some
}