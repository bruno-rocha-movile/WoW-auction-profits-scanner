package hello

/**
 * Created by bruno.rocha on 11/11/16.
 */

import java.util.*

data class Data (
        var minimumBuyouts: MutableMap<Long,Long>,
        val priceSectionIds: List<WowItem>,
        val profitSectionIds: List<WowItem>
)

class SettingsParser(val properties: Properties) {
    fun createSettingsFromProperties(): Data {
        return Data (
                minimumBuyouts = emptyMutableAuctionMap(),
                priceSectionIds = getPropertyIfSet("priceSectionInfo").parsedPriceData(),
                profitSectionIds = getPropertyIfSet("profitSectionInfo").parsedProfitData()
        )
    }

    fun getPropertyIfSet(property: String): String {
        if (!properties.containsKey(property)) {
            println("Missing "+property+" configuration")
            System.exit(1)
            return ""
        }
        return properties.getProperty(property)
    }
}

fun String.parsedPriceData():List<WowItem> {
    var finalParsedData:MutableList<WowItem> = mutableListOf()
    val items = this.split(",")
    for (itemData in items) {
        val splitData = itemData.split(":")
        val itemId = splitData[0].toLong()
        val itemName = splitData[1]
        finalParsedData.add(WowItem(id = itemId, name = itemName))
    }
    return finalParsedData
}

fun String.parsedProfitData():List<WowItem> {
    var finalParsedData:MutableList<WowItem> = mutableListOf()
    val items = this.split("|")
    for (itemData in items) {
        val splitData = itemData.split(":")
        val itemId = splitData[0].toLong()
        val itemName = splitData[1]
        val formulas = splitData[2].split("formula=")[1].split(",")
        var parsedFormulaList:MutableList<Long> = mutableListOf()
        for (formula in formulas) {
            val splitFormulaData = formula.split("*")
            val componentId = splitFormulaData[0].toLong()
            val amount = splitFormulaData[1].toInt()
            (1..amount).forEach {
                parsedFormulaList.add(componentId)
            }
        }
        finalParsedData.add(WowItem(id = itemId, name = itemName, formula = parsedFormulaList))
    }
    return finalParsedData
}