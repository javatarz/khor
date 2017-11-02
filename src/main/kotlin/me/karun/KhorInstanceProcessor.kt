package me.karun

internal class KhorInstanceProcessor {
  companion object {
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
}
