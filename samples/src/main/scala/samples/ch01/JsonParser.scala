package samples.ch01

trait JsonParser[T] {
  def toJson(t: T): String
  def fromJson(json: String): T
}
