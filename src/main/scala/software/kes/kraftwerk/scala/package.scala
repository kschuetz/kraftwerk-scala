package software.kes.kraftwerk

import software.kes.kraftwerk.{FloatingPointGenerator => JFloatingPointGenerator, Generator => JGenerator, Result => JResult, ValueSupply => JValueSupply}

package object scala extends LambdaAdapters {

  implicit class GeneratorWrapper[A](underlying: JGenerator[A]) {
    def toScala: Generator[A] = new Generator(underlying)
  }

  implicit class FloatingPointGeneratorWrapper[A](underlying: JFloatingPointGenerator[A]) {
    def toScala: FloatingPointGenerator[A] = new FloatingPointGenerator(underlying)
  }

  implicit class ValueSupplyWrapper[A](underlying: JValueSupply[A]) {
    def toScala: ValueSupply[A] = new ValueSupply[A](underlying)
  }

  implicit class ResultWrapper[S, A](underlying: JResult[S, A]) {
    def toScala: Result[S, A] = Result(underlying)
  }

  implicit def floatingPointGeneratorToGenerator[A](fpg: FloatingPointGenerator[A]): Generator[A] =
    new Generator(fpg.toJava)
}
