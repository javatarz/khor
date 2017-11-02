package me.karun

import kotlin.reflect.KClass

class KhorProcessor(private vararg val khors: KClass<out Khor>) {
  fun methods(): Int = khors.map { it.java }
    .map { KhorInstanceProcessor.methods(it) }
    .sum()

  fun helpText(): String {
    val helpItems = helpItems()

    val textItems = HelpTextItem.header + helpItems

    val maxMethodNameLength: Int = textItems.map { it.methodName.length }
      .max() ?: 0
    val maxDescriptionLength: Int = textItems.map { it.description.length }
      .max() ?: 0

    val printableItems = SeparatorHelpTextItem(maxMethodNameLength, maxDescriptionLength, PrintSeparator.TOP)+
      HelpTextItem.header +
      SeparatorHelpTextItem(maxMethodNameLength, maxDescriptionLength, PrintSeparator.MIDDLE)+
      helpItems +
      SeparatorHelpTextItem(maxMethodNameLength, maxDescriptionLength, PrintSeparator.BOTTOM)

    return printableItems.joinToString(separator = "\n") {
      it.printableText(maxMethodNameLength, maxDescriptionLength)
    }
  }

  private fun helpItems(): List<HelpTextItem> {
    return khors.map { it.java }
      .map { xform(it.simpleName.toLowerCase(), KhorInstanceProcessor.helpText(it)) }
      .flatten()
  }

  private fun xform(taskName: String, items: Iterable<HelpTextItem>): List<HelpTextItem> =
    items.map { HelpTextItem(methodName = taskName + " " + it.methodName, description = it.description) }
      .toList()
}
