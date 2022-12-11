package com.example.crypto.network

import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_LIST
import com.example.crypto.domain.CryptoDetail
import com.example.crypto.domain.CryptoOverview
import com.example.crypto.util.listToString
import com.example.crypto.util.removeProtocol
import com.example.crypto.database.DbNews
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCryptoOverView(
    val id: String,
    @Json(name = "market_cap_rank") val rank: Long,
    @Json(name = "image") val imgSrcUrl: String,
    @Json(name = "symbol") val ticker: String,
    @Json(name = "current_price") val currentPrice: Double,
    @Json(name = "price_change_percentage_24h") val percentageChange24h: Double?,
    @Json(name = "market_cap") val marketCap: Long
)

@JsonClass(generateAdapter = true)
data class NetworkCryptoDetail(
    val id: String,
    val links: Links,
    @Json(name = "genesis_date") val genesisDate: String?,
    val description: Description
) {
    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "homepage") val homePage: List<String>,
        @Json(name = "blockchain_site") val blockchainSite: List<String>,
        @Json(name = "twitter_screen_name") val twitterUsername: String,
        @Json(name = "facebook_username") val facebookUsername: String
    )

    @JsonClass(generateAdapter = true)
    data class Description(
        @Json(name = "en") val englishDescription: String
    )
}

@JsonClass(generateAdapter = true)
data class SearchResult(
    val coins: List<Coin>,
    val exchanges: List<Exchange>
) {
    @JsonClass(generateAdapter = true)
    data class Coin(
        val id: String,
        val name: String,
        @Json(name = "symbol") val ticker: String,
        @Json(name = "market_cap_rank") val rank: Long?, //TODO: Fix #null in search (ex query: algo)
        @Json(name = "large") val imgSrcUrl: String
    )

    @JsonClass(generateAdapter = true)
    data class Exchange(
        val id: String,
        val name: String,
        @Json(name = "market_type") val marketType: String, //TODO: check if market_type field is required here
        @Json(name = "large") val imgSrcUrl: String
    )
}

@JsonClass(generateAdapter = true)
data class ExchangeDetail(
    val name: String,
    @Json(name = "year_established") val yearEstablished: Int?,
    @Json(name = "url") val homePage: String,
    @Json(name = "image") val imgSrcUrl: String,
    @Json(name = "facebook_url") val facebookUrl: String,
    @Json(name = "reddit_url") val redditUrl: String,
    @Json(name = "twitter_handle") val twitterHandle: String,
    val centralized: Boolean,
    @Json(name = "trust_score") val trustScore: Byte?,
    @Json(name = "trust_score_rank") val rank: Int,
    @Json(name = "trade_volume_24h_btc") val tradeVolumeBtc: Double,
    val tickers: List<Ticker>
) {
    data class Ticker(
        val base: String,
        val target: String,
        @Json(name = "converted_last") val price: ConvertedValue,
        @Json(name = "converted_volume") val volume: ConvertedVolume,
        @Json(name = "trust_score") val trustScoreColor: String?
    ) {
        data class ConvertedValue(
            @Json(name = "usd") val valueInUsd: Double,
            @Json(name = "btc") val valueInBtc: Double
        )

        data class ConvertedVolume(
            @Json(name = "usd") val volumeInUsd: Double
        )
    }
}

@JsonClass(generateAdapter = true)
data class News(
    val title: String,
    @Json(name = "image") val imgSrcUrl: String,
    val url: String,
    val date: String
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
        blockchainSite = listToString(this.links.blockchainSite).removeProtocol(),
        genesisDate = this.genesisDate ?: "-",
        twitterUsername = "twitter.com/${links.twitterUsername}", //TODO: handle default url without username (twitter.com/)
        facebookUsername = "facebook.com/${links.facebookUsername}", //TODO: handle default url  without username (facebook.com/)
        englishDescription = HtmlCompat.fromHtml(
            this.description.englishDescription.replace("\n", "<br>"),
            FROM_HTML_SEPARATOR_LINE_BREAK_LIST
        ).toString()
    )
}

fun List<News>.asDatabaseModel(): Array<DbNews> {
    return map {
        DbNews(
            title = it.title,
            imgSrcUrl = it.imgSrcUrl,
            url = it.url,
            date = it.date.dropLast(9)
        )
    }.toTypedArray()
}