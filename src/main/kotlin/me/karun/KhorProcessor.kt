package me.karun

import kotlin.reflect.KClass

class KhorProcessor(private vararg val khors: KClass<out Khor>) {
  fun helpText(): String = evaluateHelpItems().evaluatePrintableString()

  private fun evaluateHelpItems(): HelpTextItems {
    val items = khors.map { it.java }
      .map { combine(it.simpleName.toLowerCase(), KhorInstanceProcessor.helpText(it)) }
      .flatten()
    return HelpTextItems(items)
  }

  private fun combine(taskName: String, items: Iterable<HelpTextItem>): List<HelpTextItem> =
    items.map { HelpTextItem(methodName = taskName + " " + it.methodName, description = it.description) }
      .toList()
}
