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
    toJava.fmap(a => f(a)).toScala

  def flatMap[B](f: A => Generator[B]): Generator[B] =
    toJava.flatMap(a => f(a).toJava).toScala

  def pair: Generator[(A, A)] =
    toJava.pair.fmap(t => (t._1, t._2)).toScala

  def triple: Generator[(A, A, A)] =
    toJava.triple.fmap(t => (t._1, t._2, t._3)).toScala

  def weighted: Weighted[Generator[A]] =
    Weighted.weighted(1, this)

  def weighted(weight: Int): Weighted[Generator[A]] =
    Weighted.weighted(weight, this)

  def option: Generator[Option[A]] =
    toJava.maybe().fmap(a => Lambda.maybeToOption(a)).toScala

  def vector: Generator[Vector[A]] = {
    val inner = Generators.sized { size =>
      Generators.aggregate(CollectionAdapters.vectorAggregator[A], size, toJava)
    }
    inner.toScala
  }
}
