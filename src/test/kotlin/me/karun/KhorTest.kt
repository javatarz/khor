package me.karun

import org.amshove.kluent.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object KhorTest : Spek({

  given("a khor") {
    val khor = Khor()

    on("performing a check") {

      it("passes") {
        khor.number() shouldBe 1
      }
    }
  }
})
