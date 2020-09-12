package dev.marksman.kraftwerk.scala

import dev.marksman.kraftwerk.{ValueSupply => JValueSupply}

class ValueSupply[A](val toJava: JValueSupply[A]) extends Iterable[A] {
  def iterator: Iterator[A] = new ValueSupplyIterator(toJava.iterator)

  private class ValueSupplyIterator(underlying: java.util.Iterator[A]) extends Iterator[A] {
    def hasNext: Boolean = underlying.hasNext

    def next(): A = underlying.next()
  }

}
