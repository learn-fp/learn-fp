# The implications of implicit keyword in scala !
Scala syntax is very simple and straightforward, that is if we ignore the infamous `implicit` keyword ! This bad reputation comes from the fact that not only it's a little confusing, it is used in couple of different ways to perform different tasks. But we can summarize it in one simple concept: __Extending the language__. We break implicit keyword usage into two parts: Implicit conversion and implicit parameters. Note that implicits has other capabilities in scala that does not concern us in this book.

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

Let's do another exercise to make sure that you're convinced that Scala means __Scalable Language__ ! Remember Java's ternary operation ? It was `String str = 1 == 2 ? "this" : "that"` which is like the following Scala code: `val str: String = if(1 == 2) "this" else "that"`.
How can we have this syntax in scala ?
```
Exercise 2: Using implicit classes, support the following syntax: '<A Boolean> ? <t: T>' returns Option[T], if boolean is true then Some(t) otherwise None.
Support also this syntax: '<t: Option[T]> | <q: T>' means t.getOrElse(q)
So that we can have the following ternary operation :
(1 == 2) ? "this" | "that"
```
[Checkout the answer here](samples/src/main/scala/samples/ch00/Ex2.scala)

## 2. Implicit parameters
In scala, any method can have explicit and implicit parameters, which means a method or function can have parameters that you don't have to passe them every time you want to call the function.
Let's say that you have a function __fetch__ that given a url, downloads it's html contents. Your function has two parameters: the URL you want to fetch and a timeout (in milliseconds) so that fetching from a slow server won't take forever to complete (perhaps it returns empty result). It looks like this:

```SCALA
def fetch(url: String, timeout: Long): String = ???
```
* Note that you can put `???` as the body of the function that you want to implement later, and compiler won't nag.

Here you can call your function like this 
```SCALA
val timeout = 2L * 60L * 1000L // Two minutes
fetch("https://github.com", timeout )
fetch("https://google.com", timeout )
fetch("https://gitlab.com", timeout )
fetch("https://github.io", timeout )
```
But usually you pass the __timeout__ parameter every time without any changes. Instead, you can declare that you want the timeout parameter implicitly from the context :

```SCALA
def fetch(url: String)(implicit timeout: Long): String = ???
```
And you can call your function like this :
```SCALA
implicit val timeout = 2L * 60L * 1000L // two minutes
fetch("https://github.com")
fetch("https://google.com")
fetch("https://github.io")

// ofcourse you can still pass the param explicitly
fetch("https://gitlab.com")(timout * 4) 
```
What happens is somehow like the scenario in implicit conversion. The compiler sees a missing parameter, but it's declared as implicit parameter, so it looks like for an implicit value defined (or imported) in context and puts the implicit value there in compile time, which means the above code is compiled as :

```SCALA
implicit val timeout = 2L * 60L * 1000L // two minutes
fetch("https://github.com")(timeout)
fetch("https://google.com")(timeout)
fetch("https://github.io")(timeout)

// ofcourse you can still pass the param explicitly
fetch("https://gitlab.com")(timout * 4) 
```
Implicit values can be defined directly (like the code above) or be imported using regular import keyword (just like implicit conversion).
Implicit values can be defined as val, var, def or even singleton objects.

### BEWARE
```
Implicit parameters are usually used where it's meaningful, not just convinient, for example you see that ExecutionContext values are usually passed around as implicit parameters, because it's something about context(obviously!). Using implicits (both conversion and parameter) can result in amazingly elegant code, or a hell of confusion. 
Think twice before using either of them.
```

### 2.1. The implicitly keyword
The `implicitly` keyword is a trick to summon an implicit value, just like an implicit parameter in a function's signature. For example, let's say there is type `JsonWriter[T]` and an instance of this type can convert an instance of `T` into a `JsonObject` and then to a Json-formatted String.

```Scala
val person = Person(name = "X" , age = 28)
val writer = implicitly[JsonWriter[Person]]
println(writer.write(person).toString)
```
And we expect to be able to run this code and the output must be something like this:
```JSON
{"name":"X","age":28}
```
As you may expect, the compiler tries to find evidence that there exist an implicit instance of `JsonWriter[Persor]' in the context and if it fail to ensure the instance is available, it raises an error and the compile would fail.

## Further reading
If you want to learn more about implicits in Scala, I suggest that you read [Li Haoyi's implicit design patterns](http://www.lihaoyi.com/post/ImplicitDesignPatternsinScala.html).