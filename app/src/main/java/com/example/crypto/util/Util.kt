package com.example.crypto.util

fun listToString(list: List<String>) =
    list.reduce { acc, next -> if (next.isNotBlank()) acc + "\n" + next.removeProtocol() else acc + "" }
    //array.reduce { acc, next -> if (next.isNotBlank()) acc + next.removeProtocol() + "\n" else acc + "" }

fun String.removeProtocol(): String {
    return if (this.contains("https://")) this.removePrefix("https://")
    else if (this.contains("http://")) this.removePrefix("http://")
    else this
}

fun formatCurrency(price: String): Double {
    return if (price.length < 5) price.toDouble() else {
        val sb = StringBuilder(price)

        for (index in price.length downTo 1 step 3) {
            sb.insert(index, ",")
        }

        sb.toString().toDouble()
    }
}

//fun String.trimSupply(length: Int) = if (this.length > length) "${this.substring(0, length+1)}..." else this