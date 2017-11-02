package me.karun.khor

fun main(args: Array<String>) {
  val processor = KhorProcessor(Say::class, Poke::class)

  println(processor.helpText())
}

class Say : Khor() {
  @Desc("say hello to NAME")
  fun hello(name: String) {
    println("Hello $name")
  }

  @Desc("say goodbye to NAME")
  fun goodbye(name: String) {
    println("Goodbye $name")
  }
}

class Poke : Khor() {
  @Desc("pokes a human")
  fun human() {
    println("Ouch!")
  }

  @Desc("pokes a bear")
  fun bear() {
    println("You're dead.")
  }
}
