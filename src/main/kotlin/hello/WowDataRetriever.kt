package hello

import util.*

class WowDataRetriever {

    fun start() {
        updateAuctionData()
    }

    fun updateAuctionData() {
        println("Retrieving latest Auction House data url...", ANSI_YELLOW)
        val api: BattleNetAPI = BattleNetAPI()
        val callResponse = api.getRightUrl()
        val response = callResponse.execute()
        if (response.isSuccessful) {
            val url = response.body().files[0].url
            println("Auction url retrieved. " + url, ANSI_WHITE)
            updatePrices(url) {
                finishUpdate(it)
            }
        } else {
            println("Failed to retrieve auction data, aborting", ANSI_RED)
            finishUpdate(0)
        }
    }

    fun updatePrices(url: String, completion: (Int) -> Unit) {
        if (url == wowApiLink) {
            print("Auction house didn't updated yet. Skipping")
            return
        }
        wowApiLink = url
        var baseURL = url.split("auctions.json")[0]
        println("Retrieving auctions", ANSI_YELLOW)
        val api = WoWApi()
        val callResponse = api.getAllAuctions(baseURL)
        val response = callResponse.execute()
        if (response.isSuccessful) {
            val auctions = response.body().auctions
            checkIfLowerPrice(auctions)
            completion(1)
        } else {
            println("Failed to retrieve auction data, aborting", ANSI_RED)
            completion(0)
        }
    }

    fun checkIfLowerPrice(auctions: List<Auctions>) {
        println("Auctions retrieved. Checking lower prices", ANSI_YELLOW)
        resetPrices()
        auctions.forEach { auction ->
            val currentLowerPrice: Long? = data.minimumBuyouts[auction.item]
            if (currentLowerPrice == null || auction.unitPrice() < currentLowerPrice) {
                data.minimumBuyouts[auction.item] = auction.unitPrice()
            }
        }
    }

    fun resetPrices() {
        data.minimumBuyouts = emptyMutableAuctionMap()
    }

    fun finishUpdate(result: Int) {
        if (result == 1) {
            println("Auction data updated.", ANSI_GREEN)
            println("Waiting 30 minutes...", ANSI_BLUE)
            Thread.sleep(1000 * 60 * 30)
        } else {
            println("Failed to update auction data.", ANSI_RED)
        }
        updateAuctionData()
    }
}
