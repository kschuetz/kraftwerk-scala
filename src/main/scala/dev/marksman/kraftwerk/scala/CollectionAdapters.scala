package dev.marksman.kraftwerk.scala

import com.jnape.palatable.lambda.functions.builtin.fn1.Id
import dev.marksman.kraftwerk.aggregator.Aggregator

object CollectionAdapters {

  def vectorAggregator[A]: Aggregator[A, Vector[A], Vector[A]] =
    Aggregator.aggregator(fn0(Vector.empty[A]),
      fn2[Vector[A], A, Vector[A]](_ :+ _),
      Id.id[Vector[A]])

}
