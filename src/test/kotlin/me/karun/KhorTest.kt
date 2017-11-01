package me.karun

import org.amshove.kluent.`should be`
import org.amshove.kluent.`should contain all`
import org.amshove.kluent.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object KhorTest : Spek({

  given("A CLI implementation using Khor") {
    val cli: Khor = MyCLI()

    on("being processed") {
      val processor = KhorProcessor()

      it("can count the number of methods") {
        processor.methods(cli) shouldBe 2
      }

      it("provide a readable description of the methods") {
        val expected = listOf(
          HelpTextItem("hello", "say hello to NAME"),
          HelpTextItem("goodbye", "say goodbye to NAME")
        )
        val actual = processor.helpText(cli)

        actual.count() `should be` expected.count()
        actual `should contain all` expected
      }
    }
  }
})

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
