package hello

/**
 * Created by bruno.rocha on 11/11/16.
 */

import java.util.*

data class Data (
        var minimumBuyouts: MutableMap<Long,Long>,
        val priceSectionIds: List<Long>,
        val profitSectionIds: MutableMap<Long,MutableList<Long>>
)

class SettingsParser(val properties: Properties) {
    fun createSettingsFromProperties(): Data {
        val defaults = Data(minimumBuyouts = emptyMutableAuctionMap(), priceSectionIds = listOf(), profitSectionIds = emptyMutableProfitMap())
        return Data (
                minimumBuyouts = emptyMutableAuctionMap(),
                priceSectionIds = (getPropertyIfSet("priceSectionIds", defaults.priceSectionIds, String::toString) as String).split(",").filter { it.isNotBlank() }.map { it.toLong() },
                profitSectionIds = (getPropertyIfSet("profitSectionIds", defaults.profitSectionIds, String::toString) as String).parsedProfitData()
        )
    }

    fun <T> getPropertyIfSet(property: String, default: T, conversion: (String) -> T): T {
        if (!properties.containsKey(property)) {
            return default
        }
        try {
            return conversion(properties.getProperty(property))
        } catch (e: Exception) {
            return default
        }
    }
}

fun String.parsedProfitData():MutableMap<Long,MutableList<Long>> {
    var finalParsedData:MutableMap<Long,MutableList<Long>> = emptyMutableProfitMap()
    val items = this.split("|")
    for (itemData in items) {
        val splitData = itemData.split(":")
        val itemId = splitData[0].toLong()
        val formulas = splitData[1].split("formula=")[1].split(",")
        var parsedFormulaList:MutableList<Long> = mutableListOf()
        for (formula in formulas) {
            val splitFormulaData = formula.split("*")
            val componentId = splitFormulaData[0].toLong()
            val amount = splitFormulaData[1].toInt()
            (1..amount).forEach {
                parsedFormulaList.add(componentId)
            }
        }
        finalParsedData[itemId] = parsedFormulaList
    }
    return finalParsedData
}