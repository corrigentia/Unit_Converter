// private const val FOUR = 4

fun parseCardNumber(cardNumber: String): Long {
    // TODO
    if (Regex("(\\d{4} ){3}\\d{4}").matches(cardNumber)) {
        /*
    val cardNumberSplit = cardNumber.split(" ")
    // if (cardNumber.matches(Regex("digit4s([0-9]{4}) digit4s digit4s digit4s"))) {
    if (cardNumberSplit.size == FOUR) {
        for (group in cardNumberSplit) {
            if (!group.matches(Regex("[0-9]{4}"))) {
                throw NumberFormatException("Card number is incorrect.")
            }
        }
         */
        return cardNumber.split(" ").joinToString("").toLong()
    } else throw NumberFormatException("Card number is incorrect.")
}
