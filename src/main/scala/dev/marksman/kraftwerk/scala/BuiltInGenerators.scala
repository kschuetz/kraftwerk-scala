package dev.marksman.kraftwerk.scala

import com.jnape.palatable.lambda.functions._
import dev.marksman.kraftwerk.aggregator.Aggregator
import dev.marksman.kraftwerk.constraints._
import dev.marksman.kraftwerk.weights.BooleanWeights
import dev.marksman.kraftwerk.{Seed, Generator => JGenerator, Generators => JGenerators}

import scala.collection.JavaConverters._

trait BuiltInGenerators {

  def constant[A](value: A): Generator[A] =
    JGenerators.constant(value).toScala

  def generateBoolean: Generator[Boolean] =
    JGenerators.generateBoolean.toScala.asInstanceOf[Generator[Boolean]]

  def generateBoolean(weights: BooleanWeights): Generator[Boolean] =
    JGenerators.generateBoolean(weights).toScala.asInstanceOf[Generator[Boolean]]

  def generateDouble: FloatingPointGenerator[Double] =
    JGenerators.generateDouble.toScala.asInstanceOf[FloatingPointGenerator[Double]]

  def generateDoubleFractional: FloatingPointGenerator[Double] =
    JGenerators.generateDoubleFractional.toScala.asInstanceOf[FloatingPointGenerator[Double]]

  def generateDouble(range: DoubleRange): FloatingPointGenerator[Double] =
    JGenerators.generateDouble(range).toScala.asInstanceOf[FloatingPointGenerator[Double]]

  def generateFloat: FloatingPointGenerator[Float] =
    JGenerators.generateFloat.toScala.asInstanceOf[FloatingPointGenerator[Float]]

  def generateFloatFractional: FloatingPointGenerator[Float] =
    JGenerators.generateFloatFractional.toScala.asInstanceOf[FloatingPointGenerator[Float]]

  def generateFloat(range: FloatRange): FloatingPointGenerator[Float] =
    JGenerators.generateFloat(range).toScala.asInstanceOf[FloatingPointGenerator[Float]]

  def generateInt: Generator[Int] =
    JGenerators.generateInt.toScala.asInstanceOf[Generator[Int]]

  def generateInt(range: IntRange): Generator[Int] =
    JGenerators.generateInt(range).toScala.asInstanceOf[Generator[Int]]

  def generateIntIndex(bound: Int): Generator[Int] =
    JGenerators.generateIntIndex(bound).toScala.asInstanceOf[Generator[Int]]

  def generateLong: Generator[Long] =
    JGenerators.generateLong.toScala.asInstanceOf[Generator[Long]]

  def generateLong(range: LongRange): Generator[Long] =
    JGenerators.generateLong(range).toScala.asInstanceOf[Generator[Long]]

  def generateLongIndex(bound: Long): Generator[Long] =
    JGenerators.generateLongIndex(bound).toScala.asInstanceOf[Generator[Long]]

  def generateByte: Generator[Byte] =
    JGenerators.generateByte.toScala.asInstanceOf[Generator[Byte]]

  def generateByte(range: ByteRange): Generator[Byte] =
    JGenerators.generateByte(range).toScala.asInstanceOf[Generator[Byte]]

  def generateShort: Generator[Short] =
    JGenerators.generateShort.toScala.asInstanceOf[Generator[Short]]

  def generateShort(range: ShortRange): Generator[Short] =
    JGenerators.generateShort(range).toScala.asInstanceOf[Generator[Short]]

  def generateChar: Generator[Char] =
    JGenerators.generateChar.toScala.asInstanceOf[Generator[Char]]

  def generateChar(range: CharRange): Generator[Char] =
    JGenerators.generateChar(range).toScala.asInstanceOf[Generator[Char]]

  def generateGaussian: Generator[Double] =
    JGenerators.generateGaussian.toScala.asInstanceOf[Generator[Double]]

  def generateByteArray: Generator[Array[Byte]] =
    JGenerators.generateByteArray.toScala.asInstanceOf[Generator[Array[Byte]]]

  def generateByteArray(size: Int): Generator[Array[Byte]] =
    JGenerators.generateByteArray(size).toScala.asInstanceOf[Generator[Array[Byte]]]

  def generateBoxedPrimitive: Generator[Any] =
    JGenerators.generateBoxedPrimitive.toScala.asInstanceOf[Generator[Any]]

  def generateSeed: Generator[Seed] =
    JGenerators.generateSeed.toScala

  def generateSize: Generator[Int] =
    JGenerators.generateSize.toScala.asInstanceOf[Generator[Int]]

  def generateSize(sizeRange: IntRange): Generator[Int] =
    JGenerators.generateSize(sizeRange).toScala.asInstanceOf[Generator[Int]]

  def sized[A](f: Int => Generator[A]): Generator[A] =
    JGenerators.sized(new Fn1[Integer, JGenerator[A]] {
      def checkedApply(a: Integer): JGenerator[A] = f(a).toJava
    }).toScala

  def sizedMinimum[A](minimum: Int, f: Int => Generator[A]): Generator[A] =
    JGenerators.sizedMinimum(minimum, new Fn1[Integer, JGenerator[A]] {
      def checkedApply(a: Integer): JGenerator[A] = f(a).toJava
    }).toScala

  def aggregate[A, Builder, Out](aggregator: Aggregator[A, Builder, Out],
                                 elements: Iterable[Generator[A]]): Generator[Out] =
    JGenerators.aggregate(aggregator, elements.map(_.toJava).asJava).toScala

  def aggregate[A, Builder, Out](aggregator: Aggregator[A, Builder, Out],
                                 size: Int,
                                 gen: Generator[A]): Generator[Out] =
    JGenerators.aggregate(aggregator, size, gen.toJava).toScala

  def aggregate[A, Builder, Out](aggregator: Aggregator[A, Builder, Out],
                                 sizeRange: IntRange,
                                 gen: Generator[A]): Generator[Out] =
    JGenerators.aggregate(aggregator, sizeRange, gen.toJava).toScala

  def generateProduct[A, B, Out](a: Generator[A],
                                 b: Generator[B],
                                 combine: (A, B) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava,
      new Fn2[A, B, Out] {
        def checkedApply(a: A, b: B): Out = combine(a, b)
      }).toScala

  def generateProduct[A, B, C, Out](a: Generator[A],
                                    b: Generator[B],
                                    c: Generator[C],
                                    combine: (A, B, C) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava,
      new Fn3[A, B, C, Out] {
        def checkedApply(a: A, b: B, c: C): Out = combine(a, b, c)
      }).toScala

  def generateProduct[A, B, C, D, Out](a: Generator[A],
                                       b: Generator[B],
                                       c: Generator[C],
                                       d: Generator[D],
                                       combine: (A, B, C, D) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava,
      new Fn4[A, B, C, D, Out] {
        def checkedApply(a: A, b: B, c: C, d: D): Out = combine(a, b, c, d)
      }).toScala

  def generateProduct[A, B, C, D, E, Out](a: Generator[A],
                                          b: Generator[B],
                                          c: Generator[C],
                                          d: Generator[D],
                                          e: Generator[E],
                                          combine: (A, B, C, D, E) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava,
      new Fn5[A, B, C, D, E, Out] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E): Out = combine(a, b, c, d, e)
      }).toScala

  def generateProduct[A, B, C, D, E, F, Out](a: Generator[A],
                                             b: Generator[B],
                                             c: Generator[C],
                                             d: Generator[D],
                                             e: Generator[E],
                                             f: Generator[F],
                                             combine: (A, B, C, D, E, F) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava, f.toJava,
      new Fn6[A, B, C, D, E, F, Out] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E, f: F): Out = combine(a, b, c, d, e, f)
      }).toScala

  def generateProduct[A, B, C, D, E, F, G, Out](a: Generator[A],
                                                b: Generator[B],
                                                c: Generator[C],
                                                d: Generator[D],
                                                e: Generator[E],
                                                f: Generator[F],
                                                g: Generator[G],
                                                combine: (A, B, C, D, E, F, G) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava, f.toJava, g.toJava,
      new Fn7[A, B, C, D, E, F, G, Out] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E, f: F, g: G): Out = combine(a, b, c, d, e, f, g)
      }).toScala

  def generateProduct[A, B, C, D, E, F, G, H, Out](a: Generator[A],
                                                   b: Generator[B],
                                                   c: Generator[C],
                                                   d: Generator[D],
                                                   e: Generator[E],
                                                   f: Generator[F],
                                                   g: Generator[G],
                                                   h: Generator[H],
                                                   combine: (A, B, C, D, E, F, G, H) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava, f.toJava, g.toJava, h.toJava,
      new Fn8[A, B, C, D, E, F, G, H, Out] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H): Out = combine(a, b, c, d, e, f, g, h)
      }).toScala

  def generateProduct[A, B, C, D, E, F, G, H, I, Out](a: Generator[A],
                                                      b: Generator[B],
                                                      c: Generator[C],
                                                      d: Generator[D],
                                                      e: Generator[E],
                                                      f: Generator[F],
                                                      g: Generator[G],
                                                      h: Generator[H],
                                                      i: Generator[I],
                                                      combine: (A, B, C, D, E, F, G, H, I) => Out): Generator[Out] = {
    val group1 = generateProduct[A, B, C, D, E, F, G, H, (A, B, C, D, E, F, G, H)](a, b, c, d, e, f, g, h,
      (aa, bb, cc, dd, ee, ff, gg, hh) => (aa, bb, cc, dd, ee, ff, gg, hh))

    generateProduct[(A, B, C, D, E, F, G, H), I, Out](group1, i, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii) => combine(aa, bb, cc, dd, ee, ff, gg, hh, ii)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, Out](a: Generator[A],
                                                         b: Generator[B],
                                                         c: Generator[C],
                                                         d: Generator[D],
                                                         e: Generator[E],
                                                         f: Generator[F],
                                                         g: Generator[G],
                                                         h: Generator[H],
                                                         i: Generator[I],
                                                         j: Generator[J],
                                                         combine: (A, B, C, D, E, F, G, H, I, J) => Out): Generator[Out] = {
    val group1 = generateProduct[A, B, C, D, E, F, G, H, (A, B, C, D, E, F, G, H)](a, b, c, d, e, f, g, h,
      (aa, bb, cc, dd, ee, ff, gg, hh) => (aa, bb, cc, dd, ee, ff, gg, hh))
    generateProduct[(A, B, C, D, E, F, G, H), I, J, Out](group1, i, j, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, Out](a: Generator[A],
                                                            b: Generator[B],
                                                            c: Generator[C],
                                                            d: Generator[D],
                                                            e: Generator[E],
                                                            f: Generator[F],
                                                            g: Generator[G],
                                                            h: Generator[H],
                                                            i: Generator[I],
                                                            j: Generator[J],
                                                            k: Generator[K],
                                                            combine: (A, B, C, D, E, F, G, H, I, J, K) => Out): Generator[Out] = {
    val group1 = generateProduct[A, B, C, D, E, F, G, H, (A, B, C, D, E, F, G, H)](a, b, c, d, e, f, g, h,
      (aa, bb, cc, dd, ee, ff, gg, hh) => (aa, bb, cc, dd, ee, ff, gg, hh))

    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, Out](group1, i, j, k, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, Out](a: Generator[A],
                                                               b: Generator[B],
                                                               c: Generator[C],
                                                               d: Generator[D],
                                                               e: Generator[E],
                                                               f: Generator[F],
                                                               g: Generator[G],
                                                               h: Generator[H],
                                                               i: Generator[I],
                                                               j: Generator[J],
                                                               k: Generator[K],
                                                               l: Generator[L],
                                                               combine: (A, B, C, D, E, F, G, H, I, J, K, L) => Out): Generator[Out] = {
    val group1 = generateProduct[A, B, C, D, E, F, G, H, (A, B, C, D, E, F, G, H)](a, b, c, d, e, f, g, h,
      (aa, bb, cc, dd, ee, ff, gg, hh) => (aa, bb, cc, dd, ee, ff, gg, hh))

    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, L, Out](group1, i, j, k, l, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk, ll) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll)
    })
  }

}
