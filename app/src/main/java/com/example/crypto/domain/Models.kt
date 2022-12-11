package com.example.crypto.domain

data class CryptoOverview(
    var id: String,
    var rank: Long,
    var imgSrcUrl: String,
    var ticker: String,
    var currentPrice: Double,
    var percentageChange24h: Double,
    var marketCap: Long
)

data class CryptoDetail(
    var id: String,
    var homePage: String,
    var blockchainSite: String,
    var genesisDate: String,
    var twitterUsername: String,
    var facebookUsername: String,
    var englishDescription: String,
)

data class SearchCoin(
    val id: String,
    val name: String,
    val ticker: String,
    val rank: Long,
    val imgSrcUrl: String
)

data class SearchExchange(
    val id: String,
    val name: String,
    val marketType: String,
    val imgSrcUrl: String
)