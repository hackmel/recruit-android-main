package nz.co.test.transactions.services

import retrofit2.Call
import retrofit2.http.GET

interface TransactionsService {
    @GET("test-data.json")
    fun retrieveTransactions(): Call<Array<Transaction>>?
}

