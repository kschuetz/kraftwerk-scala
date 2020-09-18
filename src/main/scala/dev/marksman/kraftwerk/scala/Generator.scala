package dev.marksman.kraftwerk.scala

import dev.marksman.kraftwerk.{GeneratorParameters, Generators, Seed, Weighted, Generator => JGenerator}

class Generator[A](val toJava: JGenerator[A]) {
  def run(generatorParameters: GeneratorParameters, initialSeed: Seed): ValueSupply[A] =
    toJava.run(generatorParameters, initialSeed).toScala

  def run(generatorParameters: GeneratorParameters): ValueSupply[A] =
    toJava.run(generatorParameters).toScala

  def run(initialSeed: Seed): ValueSupply[A] =
    toJava.run(initialSeed).toScala

  def run: ValueSupply[A] =
    toJava.run.toScala

  def map[B](f: A => B): Generator[B] =
    toJava.fmap[B](fn1(a => f(a))).toScala

  def flatMap[B](f: A => Generator[B]): Generator[B] =
    toJava.flatMap[B](fn1(a => f(a).toJava)).toScala

  def pair: Generator[(A, A)] =
    toJava.pair.fmap[(A, A)](fn1(t => t.toScala)).toScala

  def triple: Generator[(A, A, A)] =
    toJava.triple.fmap[(A, A, A)](fn1(t => t.toScala)).toScala

  def weighted: Weighted[Generator[A]] =
    Weighted.weighted(1, this)

  def weighted(weight: Int): Weighted[Generator[A]] =
    Weighted.weighted(weight, this)

  def option: Generator[Option[A]] =
    toJava.maybe().fmap[Option[A]](fn1(a => a.toScala)).toScala

  def vector: Generator[Vector[A]] = {
    val inner = Generators.sized[Vector[A]](fn1 { size =>
      Generators.aggregate(CollectionAdapters.vectorAggregator[A], size, toJava)
    })
    inner.toScala
  }
}
