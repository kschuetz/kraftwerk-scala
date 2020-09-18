package dev.marksman.kraftwerk.scala

import com.jnape.palatable.lambda.adt.Maybe
import com.jnape.palatable.lambda.functions.{Fn0, Fn1, Fn2, Fn3}

//noinspection ConvertExpressionToSAM
trait LambdaAdapters {

  def maybeToOption[A](maybe: Maybe[A]): Option[A] =
    maybe.`match`(_ => None, a => Some(a))

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

  implicit class LambdaFn1Wrapper[A, B](f: Fn1[A, B]) {
    def toScala: A => B = a => f.apply(a)
  }

  implicit class LambdaFn2Wrapper[A, B, C](f: Fn2[A, B, C]) {
    def toScala: (A, B) => C = (a, b) => f.apply(a, b)
  }

  implicit class LambdaFn3Wrapper[A, B, C, D](f: Fn3[A, B, C, D]) {
    def toScala: (A, B, C) => D = (a, b, c) => f.apply(a, b, c)
  }

  implicit class ScalaFn1Wrapper[A, B](f: A => B) {
    def toJava: Fn1[A, B] = fn1(f)
  }

  implicit class ScalaFn2Wrapper[A, B, C](f: (A, B) => C) {
    def toJava: Fn2[A, B, C] = fn2(f)
  }

  implicit class ScalaFn3Wrapper[A, B, C, D](f: (A, B, C) => D) {
    def toJava: Fn3[A, B, C, D] = fn3(f)
  }

}
