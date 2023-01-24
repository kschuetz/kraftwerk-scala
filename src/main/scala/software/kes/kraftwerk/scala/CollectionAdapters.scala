package software.kes.kraftwerk.scala

import com.jnape.palatable.lambda.functions.builtin.fn1.Id
import software.kes.enhancediterables.FiniteIterable
import software.kes.kraftwerk.aggregator.Aggregator

import java.{lang, util}
import scala.collection.mutable.ListBuffer

object CollectionAdapters {

  def vectorAggregator[A]: Aggregator[A, Vector[A], Vector[A]] =
    Aggregator.aggregator(fn0(Vector.empty[A]),
      fn2[Vector[A], A, Vector[A]](_ :+ _),
      Id.id[Vector[A]])

  def listAggregator[A]: Aggregator[A, ListBuffer[A], List[A]] =
    Aggregator.aggregator(fn0(ListBuffer.empty[A]),
      fn2[ListBuffer[A], A, ListBuffer[A]](_ :+ _),
      fn1(lb => lb.toList))

  def finiteIterable[A](seq: Seq[A]): FiniteIterable[A] =
    new FiniteIterable[A] {
      def iterator(): util.Iterator[A] = iteratorToJavaIterator(seq.iterator)
    }

  def finiteIterable[A](set: Set[A]): FiniteIterable[A] =
    new FiniteIterable[A] {
      def iterator(): util.Iterator[A] = iteratorToJavaIterator(set.iterator)
    }

  def vectorFromJavaIterable[A](input: java.lang.Iterable[A]): Vector[A] = {
    val builder = Vector.newBuilder[A]
    val iter = input.iterator()
    while (iter.hasNext) {
      val elem = iter.next()
      builder += elem
    }
    builder.result()
  }


  private def iteratorToJavaIterator[A](input: Iterator[A]): java.util.Iterator[A] = new java.util.Iterator[A] {
    def hasNext: Boolean = input.hasNext

    def next(): A = input.next()
  }

  private def iterableToJavaIterable[A](input: Iterable[A]): java.lang.Iterable[A] = new lang.Iterable[A] {
    def iterator(): util.Iterator[A] = {
      val iter = input.iterator
      new util.Iterator[A] {
        def hasNext: Boolean = iter.hasNext

        def next(): A = iter.next()
      }
    }
  }
}
