package samples.common

object OptionHelper {

  implicit class OptionHelper[T](t: T) {
    def some : Option[T] = Some(t)
  }

  def none[T]: Option[T] = None
}
