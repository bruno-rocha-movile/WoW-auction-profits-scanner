package hello

/**
 * Created by bruno.rocha on 11/6/16.
 */

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import util.formattedHTMLWowPrice

var data: Data = Data(minimumBuyouts = emptyMutableAuctionMap(), priceSectionIds = listOf(), profitSectionIds = emptyMutableProfitMap())

@RestController
class WebPageDisplayer {
    @RequestMapping("/")
    fun index(): String {
        return minimumBuyouts() + "<br><br>" + profitData()
    }

    fun minimumBuyouts():String {
        var page = "Minimum Buyouts:<br>"
        for (id in data.priceSectionIds) {
            if (data.minimumBuyouts[id] == null) {
                page += id.toString() + ": No auctions <br>"
                continue
            }
            page += id.toString() + ": " + data.minimumBuyouts[id]?.formattedHTMLWowPrice() + "<br>"
        }
        return page
    }

    fun profitData():String {
        var page = "Profit Data:<br>"
        for ((id,formula) in data.profitSectionIds) {
            if (data.minimumBuyouts[id] == null) {
                page += id.toString() + ": Item has no auctions! <br>"
                continue
            }
            var craftPrice: Double = 0.0
            for (componentId in formula) {
                if (data.minimumBuyouts[componentId] == null) {
                    page += id.toString() + ": One of the components of this item has no auctions. <br>"
                    continue
                }
                craftPrice += data.minimumBuyouts[componentId]!!.toDouble()
            }
            val minimumBuyout: Double = data.minimumBuyouts[id]!!.toDouble()
            val sellCost = minimumBuyout * 0.95
            var pct = sellCost/craftPrice
            var result = "PROFIT"
            if (pct > 1) {
                pct -= 1
            } else {
                pct = 1 - pct
                result = "LOSS"
            }
            page += id.toString() + ": Minimum buyout is " + data.minimumBuyouts[id]?.formattedHTMLWowPrice() + " which is a " + String.format("%.2f",(pct*100)) + "% " + result +" after AH's cut (craft price: "+craftPrice.toLong().formattedHTMLWowPrice()+")<br>"
        }
        return page
    }
}

//Helpers

fun emptyMutableAuctionMap(): MutableMap<Long,Long> = mutableMapOf()
fun emptyMutableProfitMap(): MutableMap<Long,MutableList<Long>> = mutableMapOf()

