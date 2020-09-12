package dev.marksman.kraftwerk.scala

import com.jnape.palatable.lambda.adt.Maybe
import com.jnape.palatable.lambda.functions.Fn1

object Lambda {
  def maybeToOption[A](maybe: Maybe[A]): Option[A] =
    maybe.`match`(_ => None, a => Some(a))

  def optionToMaybe[A](option: Option[A]): Maybe[A] =
    option match {
      case Some(a) => Maybe.just(a)
      case None => Maybe.nothing()
    }

  def fn1[A, B](f: A => B): Fn1[A, B] = new Fn1[A, B] {
    def checkedApply(a: A): B = f(a)
  }
}
