package com.example.crypto.network

import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_LIST
import com.example.crypto.domain.CryptoDetail
import com.example.crypto.domain.CryptoOverview
import com.example.crypto.util.arrayToString
import com.example.crypto.util.removeProtocol
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCryptoOverView(
    var id: String,
    @Json(name = "market_cap_rank") var rank: Long,
    @Json(name = "image") var imgSrcUrl: String,
    @Json(name = "symbol") var ticker: String,
    @Json(name = "current_price") var currentPrice: Double,
    @Json(name = "price_change_percentage_24h") var percentageChange24h: Double?,
    @Json(name = "market_cap") var marketCap: Long
)

@JsonClass(generateAdapter = true)
data class NetworkCryptoDetail(
    var id: String,
    var links: Links,
    @Json(name = "genesis_date") var genesisDate: String?,
    var description: Description
)

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "homepage") var homePage: Array<String>,
    @Json(name = "blockchain_site") var blockchainSite: Array<String>,
    @Json(name = "twitter_screen_name") var twitterUsername: String,
    @Json(name = "facebook_username") var facebookUsername: String
)

data class Description(
    @Json(name = "en") var englishDescription: String
)

fun List<NetworkCryptoOverView>.asDomainModel(): List<CryptoOverview> {
    return map {
        CryptoOverview(
            id = it.id,
            rank = it.rank,
            imgSrcUrl = it.imgSrcUrl,
            ticker = it.ticker,
            currentPrice = it.currentPrice,
            percentageChange24h = it.percentageChange24h ?: 0.0,
            marketCap = it.marketCap
        )
    }
}

fun NetworkCryptoDetail.asDomainModel(): CryptoDetail {
    return CryptoDetail(
        id = this.id,
        homePage = this.links.homePage[0].removeProtocol(),
        blockchainSite = arrayToString(this.links.blockchainSite).removeProtocol(),
        genesisDate = this.genesisDate ?: "-",
        twitterUsername = "twitter.com/${links.twitterUsername}", //TODO: handle default url without username (twitter.com/)
        facebookUsername = "facebook.com/${links.facebookUsername}", //TODO: handle default url  without username (facebook.com/)
        englishDescription = HtmlCompat.fromHtml(
            this.description.englishDescription.replace("\n", "<br>"),
            FROM_HTML_SEPARATOR_LINE_BREAK_LIST).toString()
    )
}
