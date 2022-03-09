package nz.co.test.transactions.services

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.format.DateTimeFormatter

object OffsetDateTimeAdapter {
    @ToJson
    fun toJson(value: java.time.OffsetDateTime): String {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(value)
    }

    @FromJson
    fun fromJson(value: String): java.time.OffsetDateTime {
        return java.time.OffsetDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

}