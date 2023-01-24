package software.kes.kraftwerk.scala

object Sandbox extends BuiltInGenerators {
  def main(args: Array[String]): Unit = {
    generateInt
      .option
      .vector
      .run
      .take(5)
      .foreach(println)
  }
}
