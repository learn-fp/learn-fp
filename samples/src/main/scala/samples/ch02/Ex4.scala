package samples.ch02

object Ex4 {
  import SemigroupSyntax._
  import samples.common.OptionHelper._

  def reduce[T: Semigroup](list: List[T]): Option[T] = list match {
    case Nil => none[T]
    case head :: Nil => head.some
    case head :: theRest => reduce(theRest).map(head |+| _)
  }
}
