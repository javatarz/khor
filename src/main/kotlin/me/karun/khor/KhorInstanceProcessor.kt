package me.karun.khor

internal class KhorInstanceProcessor {
  companion object {
    fun helpText(khorClass: Class<out Khor>): Iterable<HelpTextItem> {
      return khorClass.methods
        .filter { it.isAnnotationPresent(Desc::class.java) }
        .map { HelpTextItem(it.name, it.getAnnotation(Desc::class.java).description) }
        .toList()
    }
  }
}
