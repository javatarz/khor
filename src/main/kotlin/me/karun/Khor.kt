package me.karun

import kotlin.reflect.KClass

abstract class Khor

class KhorInstanceProcessor {
  fun methods(khorInstance: Class<out Khor>): Int {
    return khorInstance.methods
      .filter { m -> m.isAnnotationPresent(Desc::class.java) }
      .count()
  }

  fun helpText(khorInstance: Class<out Khor>): Iterable<HelpTextItem> {
    return khorInstance.methods
      .filter { it.isAnnotationPresent(Desc::class.java) }
      .map { HelpTextItem(it.name, it.getAnnotation(Desc::class.java).description) }
      .toList()
  }
}

class KhorProcessor(private vararg val khors: KClass<out Khor>) {
  fun methods(): Int = khors.map { it.java }
    .map { KhorInstanceProcessor().methods(it) }
    .sum()

  fun helpText(): String = khors.map { it.java }
    .map { KhorInstanceProcessor().helpText(it) }
    .joinToString("\n\n")
}

annotation class Desc(val description: String)

data class HelpTextItem(private val methodName: String, private val description: String)
