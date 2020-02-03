package samples.ch00

object Ex0 {
  implicit class IntFeatures(i: Int) {
    def isEven: Boolean = i % 2 == 0
    def isOdd: Boolean = i % 2 != 0
    def isBetween(a: Int, b: Int): Boolean = a <= i && i <= b
    def maxWith(other: Int): Int = if (i > other) i else other
    def minWith(other: Int): Int = if (i < other) i else other
  }
}
