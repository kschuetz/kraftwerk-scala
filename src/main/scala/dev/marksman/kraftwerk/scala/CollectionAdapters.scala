package dev.marksman.kraftwerk.scala

import com.jnape.palatable.lambda.functions.builtin.fn1.Id
import com.jnape.palatable.lambda.functions.{Fn0, Fn2}
import dev.marksman.kraftwerk.aggregator.Aggregator

object CollectionAdapters {

  def vectorAggregator[A]: Aggregator[A, Vector[A], Vector[A]] = {
    val f = new Fn0[Vector[A]] {
      def checkedApply(): Vector[A] = Vector.empty[A]
    }
    val f2 = new Fn2[Vector[A], A, Vector[A]] {
      def checkedApply(a: Vector[A], b: A): Vector[A] = a :+ b
    }
    Aggregator.aggregator(f, f2, Id.id[Vector[A]])
  }


  /*
  public static <A> Aggregator<A, VectorBuilder<A>, ImmutableVector<A>> vectorAggregator(int initialCapacity) {
        return aggregator(() -> VectorBuilder.builder(initialCapacity), VectorBuilder::add, VectorBuilder::build);
    }
   */
}
