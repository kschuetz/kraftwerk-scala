package software.kes.kraftwerk.scala

import software.kes.kraftwerk
import software.kes.kraftwerk.constraints.IntRange
import software.kes.kraftwerk.{GenerateFn, GeneratorParameters, Generators, Seed, Weighted, Generator => JGenerator}

class Generator[A](val toJava: JGenerator[A]) {

  def createGenerateFn(generatorParameters: GeneratorParameters): Seed => Result[Seed, A] = {
    val javaFn: GenerateFn[A] = toJava.createGenerateFn(generatorParameters)
    seed => javaFn.apply(seed).asInstanceOf[kraftwerk.Result[Seed, A]].toScala
  }

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

  def some: Generator[Some[A]] =
    toJava.fmap[Some[A]](fn1(a => Some(a))).toScala

  def vector: Generator[Vector[A]] = {
    val inner = Generators.sized[Vector[A]](fn1 { size =>
      Generators.aggregate(CollectionAdapters.vectorAggregator[A], size, toJava)
    })
    inner.toScala
  }

  def vectorOfSize(size: Int): Generator[Vector[A]] =
    Generators.aggregate(CollectionAdapters.vectorAggregator[A], size, toJava).toScala

  def vectorOfSize(sizeRange: IntRange): Generator[Vector[A]] =
    Generators.aggregate(CollectionAdapters.vectorAggregator[A], sizeRange, toJava).toScala

  def list: Generator[List[A]] = {
    val inner = Generators.sized[List[A]](fn1 { size =>
      Generators.aggregate(CollectionAdapters.listAggregator[A], size, toJava)
    })
    inner.toScala
  }

  def listOfSize(size: Int): Generator[List[A]] =
    Generators.aggregate(CollectionAdapters.listAggregator[A], size, toJava).toScala

  def listOfSize(sizeRange: IntRange): Generator[List[A]] =
    Generators.aggregate(CollectionAdapters.listAggregator[A], sizeRange, toJava).toScala

}
