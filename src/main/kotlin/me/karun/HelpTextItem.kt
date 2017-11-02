package me.karun

internal open class HelpTextItem(internal val methodName: String,
                                 internal val description: String = "") {
  open fun printableText(maxMethodNameLength: Int, maxDescriptionLength: Int): String =
    "│ ${padString(methodName, maxMethodNameLength)} │ ${padString(description, maxDescriptionLength)} │"

  operator fun plus(list: List<HelpTextItem>): List<HelpTextItem> {
    val mList = list.toMutableList()
    mList.add(0, this)

    return mList.toList()
  }

  operator fun plus(theOther: HelpTextItem): List<HelpTextItem> = listOf(this, theOther)

  private fun padString(s: String, lengthWithPadding: Int): String = s + " ".repeat(lengthWithPadding - s.length)

  companion object {
    val header = HelpTextItem("Command", "Description")
  }
}

internal class SeparatorHelpTextItem(private val methodNameLength: Int,
                                     private val descriptionLength: Int,
                                     private val type: PrintSeparator) : HelpTextItem("") {
  override fun printableText(maxMethodNameLength: Int, maxDescriptionLength: Int): String {
    return type.start +
      "─".repeat(methodNameLength) +
      type.middle +
      "─".repeat(descriptionLength) +
      type.end
  }
}

internal enum class PrintSeparator(val start: String, val middle: String, val end: String) {
  TOP("┌─", "─┬─", "─┐"),
  MIDDLE("├─", "─┼─", "─┤"),
  BOTTOM("└─", "─┴─", "─┘")
}
