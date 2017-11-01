package me.karun

open class Khor

class KhorProcessor {
  fun methods(khorInstance: Khor): Int {
    return khorInstance.javaClass.methods
      .filter { m -> m.isAnnotationPresent(Desc::class.java) }
      .count()
  }

  fun helpText(khorInstance: Khor): Iterable<HelpTextItem> {
    return khorInstance.javaClass.methods
      .filter { it.isAnnotationPresent(Desc::class.java) }
      .map { HelpTextItem(it.name, it.getAnnotation(Desc::class.java).description) }
      .toList()
  }
}

annotation class Desc(val description: String)

data class HelpTextItem(private val methodName: String, private val description: String)
