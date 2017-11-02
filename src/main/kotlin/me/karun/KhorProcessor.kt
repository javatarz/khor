package me.karun

import kotlin.reflect.KClass

class KhorProcessor(private vararg val khors: KClass<out Khor>) {
  fun methods(): Int = khors.map { it.java }
    .map { KhorInstanceProcessor.methods(it) }
    .sum()

  fun helpText(): String = khors.map { it.java }
    .map { KhorInstanceProcessor.helpText(it) }
    .joinToString("\n\n")
}
