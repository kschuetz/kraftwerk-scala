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

  def generateAlphaChar: Generator[Char] =
    JGenerators.generateAlphaChar.toScala.asInstanceOf[Generator[Char]]

  def generateAlphaUpperChar: Generator[Char] =
    JGenerators.generateAlphaUpperChar.toScala.asInstanceOf[Generator[Char]]

  def generateAlphaLowerChar: Generator[Char] =
    JGenerators.generateAlphaLowerChar.toScala.asInstanceOf[Generator[Char]]

  def generateAlphanumericChar: Generator[Char] =
    JGenerators.generateAlphanumericChar.toScala.asInstanceOf[Generator[Char]]

  def generateNumericChar: Generator[Char] =
    JGenerators.generateNumericChar.toScala.asInstanceOf[Generator[Char]]

  def generatePunctuationChar: Generator[Char] =
    JGenerators.generatePunctuationChar.toScala.asInstanceOf[Generator[Char]]

  def generateAsciiPrintableChar: Generator[Char] =
    JGenerators.generateAsciiPrintableChar.toScala.asInstanceOf[Generator[Char]]

  def generateControlChar: Generator[Char] =
    JGenerators.generateControlChar.toScala.asInstanceOf[Generator[Char]]

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

  def generateProduct[A, B, C, Out](a: Generator[A], b: Generator[B], c: Generator[C],
                                    combine: (A, B, C) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava,
      new Fn3[A, B, C, Out] {
        def checkedApply(a: A, b: B, c: C): Out = combine(a, b, c)
      }).toScala

  def generateProduct[A, B, C, D, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                       combine: (A, B, C, D) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava,
      new Fn4[A, B, C, D, Out] {
        def checkedApply(a: A, b: B, c: C, d: D): Out = combine(a, b, c, d)
      }).toScala

  def generateProduct[A, B, C, D, E, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                          e: Generator[E],
                                          combine: (A, B, C, D, E) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava,
      new Fn5[A, B, C, D, E, Out] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E): Out = combine(a, b, c, d, e)
      }).toScala

  def generateProduct[A, B, C, D, E, F, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                             e: Generator[E], f: Generator[F],
                                             combine: (A, B, C, D, E, F) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava, f.toJava,
      new Fn6[A, B, C, D, E, F, Out] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E, f: F): Out = combine(a, b, c, d, e, f)
      }).toScala

  def generateProduct[A, B, C, D, E, F, G, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                e: Generator[E], f: Generator[F], g: Generator[G],
                                                combine: (A, B, C, D, E, F, G) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava, f.toJava, g.toJava,
      new Fn7[A, B, C, D, E, F, G, Out] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E, f: F, g: G): Out = combine(a, b, c, d, e, f, g)
      }).toScala

  def generateProduct[A, B, C, D, E, F, G, H, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                   e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                   combine: (A, B, C, D, E, F, G, H) => Out): Generator[Out] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava, f.toJava, g.toJava, h.toJava,
      new Fn8[A, B, C, D, E, F, G, H, Out] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H): Out = combine(a, b, c, d, e, f, g, h)
      }).toScala

  def generateProduct[A, B, C, D, E, F, G, H, I, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                      e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                      i: Generator[I],
                                                      combine: (A, B, C, D, E, F, G, H, I) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, Out](generateTuple(a, b, c, d, e, f, g, h), i, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii) => combine(aa, bb, cc, dd, ee, ff, gg, hh, ii)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                         e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                         i: Generator[I], j: Generator[J],
                                                         combine: (A, B, C, D, E, F, G, H, I, J) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, Out](generateTuple(a, b, c, d, e, f, g, h), i, j, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                            e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                            i: Generator[I], j: Generator[J], k: Generator[K],
                                                            combine: (A, B, C, D, E, F, G, H, I, J, K) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, Out](generateTuple(a, b, c, d, e, f, g, h), i, j, k, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                               e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                               i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                               combine: (A, B, C, D, E, F, G, H, I, J, K, L) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, L, Out](generateTuple(a, b, c, d, e, f, g, h), i, j, k, l, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk, ll) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, M, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                  e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                  i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                  m: Generator[M],
                                                                  combine: (A, B, C, D, E, F, G, H, I, J, K, L, M) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, L, M, Out](generateTuple(a, b, c, d, e, f, g, h), i, j, k, l, m, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk, ll, mm) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                     e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                     i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                     m: Generator[M], n: Generator[N],
                                                                     combine: (A, B, C, D, E, F, G, H, I, J, K, L, M, N) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, L, M, N, Out](generateTuple(a, b, c, d, e, f, g, h), i, j, k, l, m, n, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk, ll, mm, nn) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                        e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                        i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                        m: Generator[M], n: Generator[N], o: Generator[O],
                                                                        combine: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, L, M, N, O, Out](generateTuple(a, b, c, d, e, f, g, h), i, j, k, l, m, n, o, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk, ll, mm, nn, oo) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                           e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                           i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                           m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                           combine: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Out](generateTuple(a, b, c, d, e, f, g, h),
      generateTuple(i, j, k, l, m, n, o, p), {
        case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp)) =>
          combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp)
      })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                              e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                              i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                              m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                              q: Generator[Q],
                                                                              combine: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, Out](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                                 e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                                 i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                                 m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                                 q: Generator[Q], r: Generator[R],
                                                                                 combine: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, R, Out](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, r, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq, rr) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                                    e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                                    i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                                    m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                                    q: Generator[Q], r: Generator[R], s: Generator[S],
                                                                                    combine: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, R, S, Out](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, r, s, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq, rr, ss) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr, ss)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                                       e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                                       i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                                       m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                                       q: Generator[Q], r: Generator[R], s: Generator[S], t: Generator[T],
                                                                                       combine: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, R, S, T, Out](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, r, s, t, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq, rr, ss, tt) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr, ss, tt)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                                          e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                                          i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                                          m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                                          q: Generator[Q], r: Generator[R], s: Generator[S], t: Generator[T],
                                                                                          u: Generator[U],
                                                                                          combine: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, R, S, T, U, Out](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, r, s, t, u, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq, rr, ss, tt, uu) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr, ss, tt, uu)
    })
  }

  def generateProduct[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                                             e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                                             i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                                             m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                                             q: Generator[Q], r: Generator[R], s: Generator[S], t: Generator[T],
                                                                                             u: Generator[U], v: Generator[V],
                                                                                             combine: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V) => Out): Generator[Out] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, R, S, T, U, V, Out](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, r, s, t, u, v, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq, rr, ss, tt, uu, vv) =>
        combine(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr, ss, tt, uu, vv)
    })
  }

  def generateTuple[A, B](a: Generator[A], b: Generator[B]): Generator[(A, B)] =
    JGenerators.generateProduct(a.toJava, b.toJava,
      new Fn2[A, B, (A, B)] {
        def checkedApply(a: A, b: B): (A, B) = (a, b)
      }).toScala

  def generateTuple[A, B, C](a: Generator[A], b: Generator[B], c: Generator[C]): Generator[(A, B, C)] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava,
      new Fn3[A, B, C, (A, B, C)] {
        def checkedApply(a: A, b: B, c: C): (A, B, C) = (a, b, c)
      }).toScala

  def generateTuple[A, B, C, D](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D]): Generator[(A, B, C, D)] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava,
      new Fn4[A, B, C, D, (A, B, C, D)] {
        def checkedApply(a: A, b: B, c: C, d: D): (A, B, C, D) = (a, b, c, d)
      }).toScala

  def generateTuple[A, B, C, D, E](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                   e: Generator[E]): Generator[(A, B, C, D, E)] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava,
      new Fn5[A, B, C, D, E, (A, B, C, D, E)] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E): (A, B, C, D, E) = (a, b, c, d, e)
      }).toScala

  def generateTuple[A, B, C, D, E, F](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                      e: Generator[E], f: Generator[F]): Generator[(A, B, C, D, E, F)] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava, f.toJava,
      new Fn6[A, B, C, D, E, F, (A, B, C, D, E, F)] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E, f: F): (A, B, C, D, E, F) = (a, b, c, d, e, f)
      }).toScala

  def generateTuple[A, B, C, D, E, F, G](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                         e: Generator[E], f: Generator[F], g: Generator[G]): Generator[(A, B, C, D, E, F, G)] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava, f.toJava, g.toJava,
      new Fn7[A, B, C, D, E, F, G, (A, B, C, D, E, F, G)] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E, f: F, g: G): (A, B, C, D, E, F, G) = (a, b, c, d, e, f, g)
      }).toScala

  def generateTuple[A, B, C, D, E, F, G, H](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                            e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H]): Generator[(A, B, C, D, E, F, G, H)] =
    JGenerators.generateProduct(a.toJava, b.toJava, c.toJava, d.toJava, e.toJava, f.toJava, g.toJava, h.toJava,
      new Fn8[A, B, C, D, E, F, G, H, (A, B, C, D, E, F, G, H)] {
        def checkedApply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H): (A, B, C, D, E, F, G, H) = (a, b, c, d, e, f, g, h)
      }).toScala

  def generateTuple[A, B, C, D, E, F, G, H, I, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                    e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                    i: Generator[I]): Generator[(A, B, C, D, E, F, G, H, I)] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, (A, B, C, D, E, F, G, H, I)](generateTuple(a, b, c, d, e, f, g, h), i, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii) => (aa, bb, cc, dd, ee, ff, gg, hh, ii)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                       e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                       i: Generator[I], j: Generator[J]): Generator[(A, B, C, D, E, F, G, H, I, J)] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, (A, B, C, D, E, F, G, H, I, J)](generateTuple(a, b, c, d, e, f, g, h), i, j, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                          e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                          i: Generator[I], j: Generator[J], k: Generator[K]): Generator[(A, B, C, D, E, F, G, H, I, J, K)] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, (A, B, C, D, E, F, G, H, I, J, K)](generateTuple(a, b, c, d, e, f, g, h), i, j, k, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                             e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                             i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L)] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, L, (A, B, C, D, E, F, G, H, I, J, K, L)](generateTuple(a, b, c, d, e, f, g, h), i, j, k, l, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk, ll) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, M, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                m: Generator[M]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L, M)] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, L, M, (A, B, C, D, E, F, G, H, I, J, K, L, M)](generateTuple(a, b, c, d, e, f, g, h), i, j, k, l, m, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk, ll, mm) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                   e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                   i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                   m: Generator[M], n: Generator[N]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, L, M, N, (A, B, C, D, E, F, G, H, I, J, K, L, M, N)](generateTuple(a, b, c, d, e, f, g, h), i, j, k, l, m, n, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk, ll, mm, nn) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                      e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                      i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                      m: Generator[M], n: Generator[N], o: Generator[O]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)] = {
    generateProduct[(A, B, C, D, E, F, G, H), I, J, K, L, M, N, O, (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)](generateTuple(a, b, c, d, e, f, g, h), i, j, k, l, m, n, o, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), ii, jj, kk, ll, mm, nn, oo) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                         e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                         i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                         m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)](generateTuple(a, b, c, d, e, f, g, h),
      generateTuple(i, j, k, l, m, n, o, p), {
        case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp)) =>
          (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp)
      })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                            e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                            i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                            m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                            q: Generator[Q]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                               e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                               i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                               m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                               q: Generator[Q], r: Generator[R]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, R, (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, r, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq, rr) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                                  e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                                  i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                                  m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                                  q: Generator[Q], r: Generator[R], s: Generator[S]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, R, S, (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, r, s, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq, rr, ss) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr, ss)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                                     e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                                     i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                                     m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                                     q: Generator[Q], r: Generator[R], s: Generator[S], t: Generator[T]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, R, S, T, (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, r, s, t, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq, rr, ss, tt) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr, ss, tt)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                                        e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                                        i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                                        m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                                        q: Generator[Q], r: Generator[R], s: Generator[S], t: Generator[T],
                                                                                        u: Generator[U]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, R, S, T, U, (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, r, s, t, u, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq, rr, ss, tt, uu) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr, ss, tt, uu)
    })
  }

  def generateTuple[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Out](a: Generator[A], b: Generator[B], c: Generator[C], d: Generator[D],
                                                                                           e: Generator[E], f: Generator[F], g: Generator[G], h: Generator[H],
                                                                                           i: Generator[I], j: Generator[J], k: Generator[K], l: Generator[L],
                                                                                           m: Generator[M], n: Generator[N], o: Generator[O], p: Generator[P],
                                                                                           q: Generator[Q], r: Generator[R], s: Generator[S], t: Generator[T],
                                                                                           u: Generator[U], v: Generator[V]): Generator[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)] = {
    generateProduct[(A, B, C, D, E, F, G, H), (I, J, K, L, M, N, O, P), Q, R, S, T, U, V, (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)](generateTuple(a, b, c, d, e, f, g, h), generateTuple(i, j, k, l, m, n, o, p), q, r, s, t, u, v, {
      case ((aa, bb, cc, dd, ee, ff, gg, hh), (ii, jj, kk, ll, mm, nn, oo, pp), qq, rr, ss, tt, uu, vv) =>
        (aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr, ss, tt, uu, vv)
    })
  }

  def generateString: Generator[String] =
    JGenerators.generateString.toScala

  //    TODO:
  //  def generateString(length: Int): Generator[String] =
  //    JGenerators.generateString(length).toScala
  //
  // TODO:
  //  def generateString(lengthRange: IntRange): Generator[String] =
  //    JGenerators.generateString(lengthRange).toScala

  def generateString(numberOfChunks: Int, chunkGenerator: Generator[String]): Generator[String] =
    JGenerators.generateString(numberOfChunks, chunkGenerator.toJava).toScala

  def generateString(numberOfChunksRange: IntRange, chunkGenerator: Generator[String]): Generator[String] =
    JGenerators.generateString(numberOfChunksRange, chunkGenerator.toJava).toScala

  def generateStringFromCharacters(chars: Generator[Character]): Generator[String] =
    JGenerators.generateStringFromCharacters(chars.toJava).toScala

  def generateStringFromCharacters(length: Int, chars: Generator[Character]): Generator[String] =
    JGenerators.generateStringFromCharacters(length, chars.toJava).toScala

  def generateStringFromCharacters(lengthRange: IntRange, chars: Generator[Character]): Generator[String] =
    JGenerators.generateStringFromCharacters(lengthRange, chars.toJava).toScala

  def generateString(first: Generator[String], more: Generator[String]*): Generator[String] =
    JGenerators.generateString(first.toJava, more.map(_.toJava): _*).toScala

  def generateIdentifier: Generator[String] =
    JGenerators.generateIdentifier.toScala

  def generateIdentifier(length: Int): Generator[String] =
    JGenerators.generateIdentifier(length).toScala

  def generateIdentifier(lengthRange: IntRange): Generator[String] =
    JGenerators.generateIdentifier(lengthRange).toScala

  def generateAlphaString: Generator[String] =
    JGenerators.generateAlphaString.toScala

  def generateAlphaString(length: Int): Generator[String] =
    JGenerators.generateAlphaString(length).toScala

  def generateAlphaString(lengthRange: IntRange): Generator[String] =
    JGenerators.generateAlphaString(lengthRange).toScala

  def generateAlphaUpperString: Generator[String] =
    JGenerators.generateAlphaUpperString.toScala

  def generateAlphaUpperString(length: Int): Generator[String] =
    JGenerators.generateAlphaUpperString(length).toScala

  def generateAlphaUpperString(lengthRange: IntRange): Generator[String] =
    JGenerators.generateAlphaUpperString(lengthRange).toScala

  def generateAlphaLowerString: Generator[String] =
    JGenerators.generateAlphaLowerString.toScala

  def generateAlphaLowerString(length: Int): Generator[String] =
    JGenerators.generateAlphaLowerString(length).toScala

  def generateAlphaLowerString(lengthRange: IntRange): Generator[String] =
    JGenerators.generateAlphaLowerString(lengthRange).toScala

  def generateAlphanumericString: Generator[String] =
    JGenerators.generateAlphanumericString.toScala

  def generateAlphanumericString(length: Int): Generator[String] =
    JGenerators.generateAlphanumericString(length).toScala

  def generateAlphanumericString(lengthRange: IntRange): Generator[String] =
    JGenerators.generateAlphanumericString(lengthRange).toScala
}
