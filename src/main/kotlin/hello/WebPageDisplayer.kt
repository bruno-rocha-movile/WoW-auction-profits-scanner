package hello

/**
 * Created by bruno.rocha on 11/6/16.
 */

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

var data: Data = Data(minimumBuyouts = emptyMutableAuctionMap(), priceSectionIds = listOf(), profitSectionIds = emptyMutableProfitMap())

@RestController
class WebPageDisplayer {
    @RequestMapping("/")
    fun index(): String {
        var page = "\n"
        for (key in data.priceSectionIds) {
            page = page + key + ": " + data.minimumBuyouts[key] + "\n"
        }
        return page
    }
}

//Helpers

fun emptyMutableAuctionMap(): MutableMap<Long,Long> = mutableMapOf()
fun emptyMutableProfitMap(): MutableMap<Long,MutableList<Long>> = mutableMapOf()