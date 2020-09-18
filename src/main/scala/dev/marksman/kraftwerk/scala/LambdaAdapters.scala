package dev.marksman.kraftwerk.scala

import com.jnape.palatable.lambda.adt.product.{Product2, Product3, Product4, Product5, Product6, Product7, Product8}
import com.jnape.palatable.lambda.adt.{Maybe, hlist}
import com.jnape.palatable.lambda.functions.{Fn0, Fn1, Fn2, Fn3}

//noinspection ConvertExpressionToSAM
trait LambdaAdapters {

  type LProduct2[A, B] = Product2[A, B]
  type LProduct3[A, B, C] = Product3[A, B, C]
  type LProduct4[A, B, C, D] = Product4[A, B, C, D]
  type LProduct5[A, B, C, D, E] = Product5[A, B, C, D, E]
  type LProduct6[A, B, C, D, E, F] = Product6[A, B, C, D, E, F]
  type LProduct7[A, B, C, D, E, F, G] = Product7[A, B, C, D, E, F, G]
  type LProduct8[A, B, C, D, E, F, G, H] = Product8[A, B, C, D, E, F, G, H]

  type LTuple2[A, B] = hlist.Tuple2[A, B]
  type LTuple3[A, B, C] = hlist.Tuple3[A, B, C]
  type LTuple4[A, B, C, D] = hlist.Tuple4[A, B, C, D]
  type LTuple5[A, B, C, D, E] = hlist.Tuple5[A, B, C, D, E]
  type LTuple6[A, B, C, D, E, F] = hlist.Tuple6[A, B, C, D, E, F]
  type LTuple7[A, B, C, D, E, F, G] = hlist.Tuple7[A, B, C, D, E, F, G]
  type LTuple8[A, B, C, D, E, F, G, H] = hlist.Tuple8[A, B, C, D, E, F, G, H]

  def maybeToOption[A](maybe: Maybe[A]): Option[A] =
    maybe.`match`(fn1(_ => None), fn1(a => Some(a)))

  def optionToMaybe[A](option: Option[A]): Maybe[A] =
    option match {
      case Some(a) => Maybe.just(a)
      case None => Maybe.nothing()
    }

  implicit class MaybeWrapper[A](maybe: Maybe[A]) {
    def toScala: Option[A] = maybeToOption(maybe)
  }

  implicit class OptionWrapper[A](option: Option[A]) {
    def toJava: Maybe[A] = optionToMaybe(option)
  }

  def fn0[A](a: => A): Fn0[A] = new Fn0[A] {
    def checkedApply(): A = a
  }

  def fn1[A, B](f: A => B): Fn1[A, B] = new Fn1[A, B] {
    def checkedApply(a: A): B = f(a)
  }

  def fn2[A, B, C](f: (A, B) => C): Fn2[A, B, C] = new Fn2[A, B, C] {
    def checkedApply(a: A, b: B): C = f(a, b)
  }

  def fn3[A, B, C, D](f: (A, B, C) => D): Fn3[A, B, C, D] = new Fn3[A, B, C, D] {
    def checkedApply(a: A, b: B, c: C): D = f(a, b, c)
  }

  implicit class LambdaFn1ToScala[A, B](f: Fn1[A, B]) {
    def toScala: A => B = a => f.apply(a)
  }

  implicit class LambdaFn2ToScala[A, B, C](f: Fn2[A, B, C]) {
    def toScala: (A, B) => C = (a, b) => f.apply(a, b)
  }

  implicit class LambdaFn3ToScala[A, B, C, D](f: Fn3[A, B, C, D]) {
    def toScala: (A, B, C) => D = (a, b, c) => f.apply(a, b, c)
  }

  implicit class LambdaFn1FromScala[A, B](f: A => B) {
    def toJava: Fn1[A, B] = fn1(f)
  }

  implicit class LambdaFn2FromScala[A, B, C](f: (A, B) => C) {
    def toJava: Fn2[A, B, C] = fn2(f)
  }

  implicit class LambdaFn3FromScala[A, B, C, D](f: (A, B, C) => D) {
    def toJava: Fn3[A, B, C, D] = fn3(f)
  }

  implicit class LambdaProduct2ToScala[A, B](t: LProduct2[A, B]) {
    def toScala: (A, B) = (t._1, t._2)
  }

  implicit class LambdaProduct3ToScala[A, B, C](t: LProduct3[A, B, C]) {
    def toScala: (A, B, C) = (t._1, t._2, t._3)
  }

  implicit class LambdaProduct4ToScala[A, B, C, D](t: LProduct4[A, B, C, D]) {
    def toScala: (A, B, C, D) = (t._1, t._2, t._3, t._4)
  }

  implicit class LambdaProduct5ToScala[A, B, C, D, E](t: LProduct5[A, B, C, D, E]) {
    def toScala: (A, B, C, D, E) = (t._1, t._2, t._3, t._4, t._5)
  }

  implicit class LambdaProduct6ToScala[A, B, C, D, E, F](t: LProduct6[A, B, C, D, E, F]) {
    def toScala: (A, B, C, D, E, F) = (t._1, t._2, t._3, t._4, t._5, t._6)
  }

  implicit class LambdaProduct7ToScala[A, B, C, D, E, F, G](t: LProduct7[A, B, C, D, E, F, G]) {
    def toScala: (A, B, C, D, E, F, G) = (t._1, t._2, t._3, t._4, t._5, t._6, t._7)
  }

  implicit class LambdaProduct8ToScala[A, B, C, D, E, F, G, H](t: LProduct8[A, B, C, D, E, F, G, H]) {
    def toScala: (A, B, C, D, E, F, G, H) = (t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8)
  }

  implicit class LambdaProduct2FromScala[A, B](t: (A, B)) {
    def toJava: LTuple2[A, B] = hlist.HList.tuple(t._1, t._2)
  }

  implicit class LambdaProduct3FromScala[A, B, C](t: (A, B, C)) {
    def toJava: LTuple3[A, B, C] = hlist.HList.tuple(t._1, t._2, t._3)
  }

  implicit class LambdaProduct4FromScala[A, B, C, D](t: (A, B, C, D)) {
    def toJava: LTuple4[A, B, C, D] = hlist.HList.tuple(t._1, t._2, t._3, t._4)
  }

  implicit class LambdaProduct5FromScala[A, B, C, D, E](t: (A, B, C, D, E)) {
    def toJava: LTuple5[A, B, C, D, E] = hlist.HList.tuple(t._1, t._2, t._3, t._4, t._5)
  }

  implicit class LambdaProduct6FromScala[A, B, C, D, E, F](t: (A, B, C, D, E, F)) {
    def toJava: LTuple6[A, B, C, D, E, F] = hlist.HList.tuple(t._1, t._2, t._3, t._4, t._5, t._6)
  }

  implicit class LambdaProduct7FromScala[A, B, C, D, E, F, G](t: (A, B, C, D, E, F, G)) {
    def toJava: LTuple7[A, B, C, D, E, F, G] = hlist.HList.tuple(t._1, t._2, t._3, t._4, t._5, t._6, t._7)
  }

  implicit class LambdaProduct8FromScala[A, B, C, D, E, F, G, H](t: (A, B, C, D, E, F, G, H)) {
    def toJava: LTuple8[A, B, C, D, E, F, G, H] = hlist.HList.tuple(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8)
  }

}
