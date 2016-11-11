package util

/**
 * Created by bruno.rocha on 11/11/16.
 */

fun Long.formattedHTMLWowPrice(): String {
    val priceString = this.toString()
    val priceLength = priceString.length
    var phase = 1
    var calculatedDigits = 0
    var copperValue: String = ""
    var silverValue: String = ""
    var goldValue: String = ""
    for (i in priceLength - 1 downTo 0) {
            //Copper
        if (phase == 1 && calculatedDigits < 2) {
            copperValue = priceString.toCharArray()[i] + copperValue
            calculatedDigits += 1
            if (calculatedDigits == 2) {
                copperValue += "<img src=/money-copper.gif>"
                phase = 2
                calculatedDigits = 0
                continue
            }
            //Silver
        } else if (phase == 2 && calculatedDigits < 2) {
            silverValue = priceString.toCharArray()[i] + silverValue
            calculatedDigits += 1
            if (calculatedDigits == 2) {
                silverValue += "<img src=/money-silver.gif>"
                phase = 3
                calculatedDigits = 0
                continue
            }
        } else if (phase == 3) {
            goldValue = priceString.toCharArray()[i] + goldValue
            if (i == 0) {
                goldValue += "<img src=/money-gold.gif>"
            }
        }
    }
    return goldValue + silverValue + copperValue
}