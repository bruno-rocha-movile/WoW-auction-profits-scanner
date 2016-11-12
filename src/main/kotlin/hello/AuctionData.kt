package hello

/**
 * Created by bruno.rocha on 11/9/16.
 */

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class BattleNetResponse(val files: List<BattleNetFileField>)

class BattleNetFileField (
            val url: String
)

interface BattleNetApiInterface {
    @GET("/wow/auction/data/{server}?locale=en_US")
    fun getAuctionData(@Path("server") server: String, @Query("apiKey") apiKey: String): Call<BattleNetResponse>
}

//==================

class WoWResponse(val realms: List<Realms>, val auctions: List<Auctions>)
class Realms(val name: String, val slug: String)

class Auctions (
    val item: Long,
    val buyout: Long,
    val quantity: Int
)

val defaultPrice: Long = 99999999999

fun Auctions.unitPrice(): Long {
    val zeroCost: Long = 0
    val isABid = this.buyout == zeroCost
    if (isABid) {
        return defaultPrice
    }
    return this.buyout / this.quantity
}

var wowApiLink = ""

interface WoWApiInterface {
    @GET("auctions.json")
    fun getAllAuctions(): Call<WoWResponse>
}