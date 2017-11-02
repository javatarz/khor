package me.karun

internal data class HelpTextItem(internal val methodName: String,
                                 internal val description: String = "") : TextItem {
  override fun printableText(methodLengths: PrintPadding): String =
    "│ ${padString(methodName, methodLengths.methodNameLength)} │ ${padString(description, methodLengths.descriptionLength)} │"

  private fun padString(s: String, lengthWithPadding: Int): String = s + " ".repeat(lengthWithPadding - s.length)

  internal operator fun plus(list: List<HelpTextItem>): List<HelpTextItem> {
    val mList = list.toMutableList()
    mList.add(0, this)

    return mList.toList()
  }

  companion object {
    val header = HelpTextItem("Command", "Description")
  }
}

internal class SeparatorHelpTextItem(private val type: PrintSeparator) : TextItem {
  override fun printableText(methodLengths: PrintPadding): String {
    return type.start +
      "─".repeat(methodLengths.methodNameLength) +
      type.middle +
      "─".repeat(methodLengths.descriptionLength) +
      type.end
  }
}

internal interface TextItem {
  fun printableText(methodLengths: PrintPadding): String

  operator fun plus(theOther: TextItem): List<TextItem> = listOf(this, theOther)
}

internal enum class PrintSeparator(val start: String, val middle: String, val end: String) {
  TOP("┌─", "─┬─", "─┐"),
  MIDDLE("├─", "─┼─", "─┤"),
  BOTTOM("└─", "─┴─", "─┘")
}
