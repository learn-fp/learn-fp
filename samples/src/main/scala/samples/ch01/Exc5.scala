package samples.ch01
// Answer for Exercise 5
object PersonOrder extends Order[Person]{
    override def eq(left: Person, right: Person): Boolean = left.age == right.age
    override def gt(left: Person, right: Person): Boolean = left.age > right.age
    override def gte(left: Person, right: Person): Boolean = left.age >= right.age
    override def lt(left: Person, right: Person): Boolean = left.age < right.age
    override def lte(left: Person, right: Person): Boolean = left.age <= right.age
}