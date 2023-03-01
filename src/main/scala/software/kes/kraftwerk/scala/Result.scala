package software.kes.kraftwerk.scala

import software.kes.kraftwerk.{Result => JResult}

case class Result[S, A](toJava: JResult[S, A]) {
  def map[B](f: A => B): Result[S, B] = Result(JResult.result(toJava.getNextState, f(toJava.getValue)))

  def value: A = toJava.getValue

  def nextState: S = toJava.getNextState

  def withNextState(newNextState: S): Result[S, A] = Result(toJava.withNextState(newNextState))

  def withValue(newValue: A): Result[S, A] = Result(toJava.withValue(newValue))
}
