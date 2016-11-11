package hello

/**
 * Created by bruno.rocha on 11/6/16.
 */

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import util.formattedHTMLWowPrice

var data: Data = Data(minimumBuyouts = emptyMutableAuctionMap(), priceSectionIds = listOf(), profitSectionIds = listOf())

@RestController
class WebPageDisplayer {
    @RequestMapping("/")
    fun index(): String {
        return minimumBuyouts() + "<br><br>" + profitData()
    }

    fun minimumBuyouts():String {
        var page = "Minimum Buyouts:<br>"
        for (item in data.priceSectionIds) {
            page += item.name + ": "
            if (data.minimumBuyouts[item.id] == null) {
                page += "No auctions"
            } else {
                page += data.minimumBuyouts[item.id]?.formattedHTMLWowPrice()
            }
            page += "<br>"
        }
        return page
    }

    fun profitData():String {
        var page = "Profit Data:<br>"
        for (item in data.profitSectionIds) {
            if (data.minimumBuyouts[item.id] == null) {
                page += item.name + ": Item has no auctions! <br>"
                continue
            }
            var craftPrice: Double = 0.0
            for (componentId in item.formula) {
                if (data.minimumBuyouts[componentId] == null) {
                    page += item.name + ": One of the components of this item has no auctions. <br>"
                    continue
                }
                craftPrice += data.minimumBuyouts[componentId]!!.toDouble()
            }
            val minimumBuyout: Double = data.minimumBuyouts[item.id]!!.toDouble()
            val sellCost = minimumBuyout * 0.95
            var pct = sellCost/craftPrice
            var result = "PROFIT"
            if (pct > 1) {
                pct -= 1
            } else {
                pct = 1 - pct
                result = "LOSS"
            }
            page += item.name + ": Minimum buyout is " + data.minimumBuyouts[item.id]?.formattedHTMLWowPrice() + " which is a " + String.format("%.2f",(pct*100)) + "% " + result +" after AH's cut (craft price: "+craftPrice.toLong().formattedHTMLWowPrice()+")<br>"
        }
        return page
    }
}

//Helpers

fun emptyMutableAuctionMap(): MutableMap<Long,Long> = mutableMapOf()
fun emptyMutableProfitMap(): MutableMap<Long,MutableList<Long>> = mutableMapOf()

