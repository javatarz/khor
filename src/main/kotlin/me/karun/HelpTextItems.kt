package me.karun

import me.karun.PrintSeparator.*

internal class HelpTextItems(private val items: List<HelpTextItem>) {
  fun evaluatePrintableString(): String = printableItems().joinToString(separator = "\n") {
    it.printableText(printPadding())
  }

  private fun printableItems(): List<TextItem> = SeparatorHelpTextItem(TOP) +
    HelpTextItem.header +
    SeparatorHelpTextItem(MIDDLE) +
    items +
    SeparatorHelpTextItem(BOTTOM)

  private fun printPadding(): PrintPadding {
    val textItems = HelpTextItem.header + items

    val methodPadding = textItems.maxOrDefault { it.methodName }
    val descriptionPadding = textItems.maxOrDefault { it.description }

    return PrintPadding(methodPadding, descriptionPadding)
  }

  private fun List<HelpTextItem>.maxOrDefault(f: (HelpTextItem) -> String): Int {
    return this.map(f)
      .map { it.length }
      .max() ?: 0
  }
}

data class PrintPadding(val methodNameLength: Int, val descriptionLength: Int)
