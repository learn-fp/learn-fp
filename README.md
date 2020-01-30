# Learn Scalaz
Learn functional programming using Scalaz! 

# About this book
This book is an introduction to functional programming using Scalaz. To read this book, you need to be familiar with basic Scala syntax including functions, classes, singleton objects, traits and pattern matching. You also need some non-basic Scala concepts(like implicits) but you'll learn them through the book. Many concepts are tought through examples and exercises. So, It's highly recommended that you work through the exercises.

# A word about functional programming
In recent years, functional programming (or FP) regained it's fame, thanks to the rise of multi-core CPUs. But concurrency is not the only aspect that FP shines at. Using even basics of FP (immutablity and purity) makes it easier to read and reason about code, but there's more. FP enables us to use the patterns brought by Category theory in order to compose programs into bigger programs. 

It's hard to provide a precise definition for functional programming, but we can begin with that FP is about trying to make our functions (in our programs) look like mathematical functions and avoid side effects and mutable data structure. When we say a language is suitable for FP, it means that the language provides syntax, tools and structures that ease using such functions. Using immutable data-structures cut both ways; sometimes it's hard to write elegant code in using just immutable data-structures, specially if the languages does'nt offer any help. FP languages usually support these criteria:
* Language should treat Functions as first class citizens: Language must provide syntax and tools for easily :
  * Defining functions
  * Pass them around
  * Create new function by composing existing ones (instead of defining them)
* Language must provide an army of immutable data-structures as defacto structures, so that libraries expose their API with immutable data-structures too
* Strong typing dramatically inreases reliability of functional code
* Supporting advanced type definitions (like higher-kinded types) makes writing functional code a lot smoother

Still, you can use FP in a language with weak support of the above features, for example, the amazing book [Mostly adequate guide to functional programming](https://mostly-adequate.gitbooks.io/mostly-adequate-guide/) uses JavaScript, which doesn't have strong typing and is terrible at supporting immutable data-structures. 

# A word about Category Theory
One of the things that makes FP amazing is the concepts that are borrowed from Category Theory (e.g Monads). These concepts are usually, powerful abstractions that make programs more composable and thus make the code more concise and elegant. Many brutally hard problems in real-world, day to day development domains have beautiful FP solutions.

Although Category theory is not easy to learn (even for mathematicians!), The good news is that you don't have to know or learn category theory to be able to use these kind of techniques! Chances are that you already used lot's of them without even hearing about Category Theory, For example if you've ever used map and flatMap on a `List` or an `Option`, you used the mighty `Monad` without ever knowing about it.

# Why I wrote this book 
I've always admired Scalaz, I wondered whether it's possible to use it in real world, in real problems. But when I tried using Scalaz in my small projects, I found that it's somehow assumed that if you want to use Scalaz, you know functional programming very well (and a little Category theory ofcourse), but if your'e not used to functional programming and it's terminology, your'e stuck. Learing resources are limited to a few video presentatinos and a few blogs (Eugene Yokota's blog is awesome). And it's how I got the idea of writing a small handbook to help others that want to use Scalaz.

# More learning resources
* Category Theory
    * Bartosz Milewski's awesome book ['Category Theory for Programmers'](https://github.com/hmemcpy/milewski-ctfp-pdf)
    * (Programming with categories)[http://brendanfong.com/programmingcats.html] (MIT course)
    * (Applied category theory)[https://ocw.mit.edu/courses/mathematics/18-s097-applied-category-theory-january-iap-2019/] (MIT course)
* Functional programming
    * (Mostly adequate guide to functional programming)[https://mostly-adequate.gitbooks.io/mostly-adequate-guide/]
    * (Functional programming in scala)[https://www.manning.com/books/functional-programming-in-scala] (AKA the red book)
* Libraries
    * (Advanced scala with cats)[https://underscore.io/training/courses/advanced-scala/]

# A Quick look
This is a work in progress, chapters zero and one are ready at this time.

## List of chapters
* Since Scala implicits are widely used throughout the book, [chapter zero](ch00_implicits.md) covers this concept (feel free to skip it if you feel confident in using implicits)
* [chapter one](ch01.md) introduces Typeclasses and Scalaz

# About
* Author: S.H.Ayat (Contact in [Twitter](https://twitter.com/fahim_ayat) and [Github](https://github.com/h-ayat))
* Editors
  * A.Parsamehr (Follow on [Twitter](https://twitter.com/parsamehram))
* Contributors
  * Waiting for your help !!! :)


This book is sponsored by [Sanjagh](https://github.com/Sanjagh)
