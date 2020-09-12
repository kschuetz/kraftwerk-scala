package dev.marksman.kraftwerk.scala

import dev.marksman.kraftwerk.{FloatingPointGenerator => JFloatingPointGenerator}

class FloatingPointGenerator[A](val toJava: JFloatingPointGenerator[A]) {
  def withNaNs(enabled: Boolean): FloatingPointGenerator[A] =
    toJava.withNaNs(enabled).toScala

  def withInfinities(enabled: Boolean): FloatingPointGenerator[A] =
    toJava.withInfinities(enabled).toScala

  def withNaNs: FloatingPointGenerator[A] = withNaNs(true)

  def withInfinities: FloatingPointGenerator[A] = withInfinities(true)
}
