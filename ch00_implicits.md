# The implications of implicit keyword in scala !
Scala syntax is very simple and straightforward, that is if we ignore the infamous `implicit` keyword ! This bad reputation comes from the fact that not only it's a little confusing, it is used in couple of different ways to perform different tasks. But we can summarize it in one simple concept: __Extending the language__. Let's face it step by step.

## 1.Implicit conversion
### 1.1 Implicit conversion with implicit functions
If you've ever struggled with JS, you know that any value can be evaluated as boolean, for example you can write `if (2)` in JS and it works. But in Scala, if you write `if (2) println("Worked")` you'll face a __Type mismatch__ compile error which says it expects that you provide a boolean inside __if__, not an integer.

Let's say we want JS style integer checks, which means that we can write `if(int)` instead of `if(int != 0)`:
```SCALA
implicit def jsStyleBoolean(a: Int): Boolean = a != 0

if (2) println("Ok!") // No compile errors, will print 'Ok!'
```
Here's how this works. Whenever Scala compiler faces the situation that it needs a value of type `T` but developer provided a value of type `Q`, before raising a compile error, it looks for an __implicit function__ of type `f: T => Q` in it's scope, if the `f` is found, it replaces the value `t` with `f(t)` and life goes on. In fact the above code is compiled into this :

```SCALA
implicit def jsStyleBoolean(a: Int): Boolean = a != 0

if (jsStyleBoolean(2)) println("Ok!")
```
Note that the implicit definition must be in target scope, for example this won't compile :
```SCALA
object Obj1 {
	implicit def jsStyleBoolean(a: Int): Boolean = a != 0
}

object Obj2 {
	if (2) println("Ok!") // compile error: Type mismatch
}
```
But this one would compile:
```SCALA
object Obj1 {
	implicit def jsStyleBoolean(a: Int): Boolean = a != 0
}

object Obj2 {
	import Obj1.jsStyleBoolean  // or import Obj1._

	if (2) println("Ok!") // Works fine
}
```
And it gets even better. Let's say that we have a value `t: T` and somewhere you call a method that does not exists : `t.mm(12) // compile error: mm is not a member of T`, but the compiler finds out that there exists a type `Q` that has a method `mm(input : Int)` and there is an __implicit function__ of type `f: T => Q` in our scope; instead of raising an error, the compiler replaces the `t.mm(12)` with `f(t).mm(12)` and life goes on. This is a very powerful and important mechanism because it helps us extending the language by adding behaviour to existing types.
We'll see more examples of this functionality soon.

### 1.2. Implicit conversion with implicit classes
Without further explanation, checkout this code:
```SCALA
implicit class OptionHelper[T](t: T) {
	def some: Option[T] = Some(t)
}
println(22.some) // Some(22)
println("Hi!".some) // Some("Hi!")
println(Person("Name" , 33)) // Some(Person(name="Name", age=33))
```
This technique is used to wrap a value inside our implicit class to add new behaviour to it. Here we've added a function `some` to values of all types (because of generic declaration), as long as `OptionHelper` is present in our scope.
Note that `implicit classes` are not much different from `implicit functions`, in fact we can use this technique just with `implicit functions`. Why don't you give it a try ?
```
Exercise 1: implement OptionHelper above using implicit functions (without using implicit classe).
```
[Checkout the answer here](samples/src/main/scala/samples/ch00/Ex1.scala)

Let's do another exercise to make sure that you're convinced that Scala means `Scalable Language`! Remember Java's ternary operation ? It was `String str = 1 == 2 ? "this" : "that"` which is like the following Scala code: `val str: String = if(1 == 2) "this" else "that"`.
How can we have this syntax in scala ?
```
Exercise 2: Using implicit classes, support the following syntax: '<A Boolean> ? <t: T>' returns Option[T], if boolean is true then Some(t) otherwise None.
Support also this syntax: '<t: Option[T]> | <q: T>' means t.getOrElse(q)
So that we can have the following ternary operation :
(1 == 2) ? "this" | "that"
```
[Checkout the answer here](samples/src/main/scala/samples/ch00/Ex2.scala)


