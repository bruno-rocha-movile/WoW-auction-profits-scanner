package hello

/**
 * Created by bruno.rocha on 11/11/16.
 */

import util.*
import java.util.*

data class Data (
        var minimumBuyouts: MutableMap<Long,Long>,
        val priceSectionIds: List<WowItem>,
        val profitSectionIds: List<WowItem>
)

class SettingsParser(val properties: Properties) {
    fun createSettingsFromProperties(): Data {
        server = getPropertyOrDie("AH Realm", "realm", String::toString).toLowerCase()
        region = getPropertyOrDie("AH Region", "region", String::toString).toLowerCase()
        apiKey = getPropertyOrDie("API Key", "apikey", String::toString)
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

    fun <T> getPropertyOrDie(description: String, property: String, conversion: (String) -> T): T {
        val settingString = "$description setting (\"$property\")"

        if (!properties.containsKey(property)) {
            println("$settingString not specified in config.properties!", ANSI_RED)
            System.exit(1)
        }

        var result: T?
        try {
            result = conversion(properties.getProperty(property))
        } catch (e: Exception) {
            println("Failed to interpret $settingString",ANSI_RED)
            System.exit(1)
            throw IllegalArgumentException()
        }
        return result
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