package hello

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by bruno.rocha on 11/9/16.
 */

class BattleNetAPI {
    private val bnApi: BattleNetApiInterface

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://us.api.battle.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        bnApi = retrofit.create(BattleNetApiInterface::class.java)
    }

    fun getRightUrl(): Call<BattleNetResponse> {
        return bnApi.getAuctionData()
    }

}

class WoWApi {
    fun getAllAuctions(url: String): Call<WoWResponse> {
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(WoWApiInterface::class.java).getAllAuctions()
    }
}