package me.karun.khor

import org.amshove.kluent.`should be`
import org.amshove.kluent.`should contain all`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object KhorInstanceProcessorSpek : Spek({

  given("A CLI implementation using Khor") {
    val khor = TestCLI()

    on("being processed") {

      it("provide a readable description of the methods") {
        val expected = listOf(
          HelpTextItem("hello", "say hello to NAME"),
          HelpTextItem("goodbye", "say goodbye to NAME")
        )
        val actual = KhorInstanceProcessor.helpText(khor.javaClass)

        actual.count() `should be` expected.count()
        actual `should contain all` expected
      }
    }
  }
})

class TestCLI : Khor() {
  @Desc("say hello to NAME")
  fun hello(name: String) {
    println("Hello $name")
  }

  @Desc("say goodbye to NAME")
  fun goodbye(name: String) {
    println("Goodbye $name")
  }
}
