package dev.marksman.kraftwerk

import dev.marksman.kraftwerk.{FloatingPointGenerator => JFloatingPointGenerator, Generator => JGenerator, ValueSupply => JValueSupply}

package object scala {

  implicit class GeneratorWrapper[A](underlying: JGenerator[A]) {
    def toScala: Generator[A] = new Generator(underlying)
  }

  implicit class FloatingPointGeneratorWrapper[A](underlying: JFloatingPointGenerator[A]) {
    def toScala: FloatingPointGenerator[A] = new FloatingPointGenerator(underlying)
  }

  implicit class ValueSupplyWrapper[A](underlying: JValueSupply[A]) {
    def toScala: ValueSupply[A] = new ValueSupply[A](underlying)
  }

  implicit def floatingPointGeneratorToGenerator[A](fpg: FloatingPointGenerator[A]): Generator[A] =
    new Generator(fpg.toJava)
}
