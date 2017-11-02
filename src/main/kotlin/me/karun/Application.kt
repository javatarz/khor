package me.karun

fun main(args: Array<String>) {
  val processor = KhorProcessor(MyCLI::class)

  println("Method count: ${processor.methods()}")
  println(processor.helpText())
}

class MyCLI : Khor() {
  @Desc("say hello to NAME")
  fun hello(name: String) {
    println("Hello $name")
  }

  @Desc("say goodbye to NAME")
  fun goodbye(name: String) {
    println("Goodbye $name")
  }
}
