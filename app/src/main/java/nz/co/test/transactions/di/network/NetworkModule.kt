package nz.co.test.transactions.di.network

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import nz.co.test.transactions.services.BigDecimalAdapter
import nz.co.test.transactions.services.OffsetDateTimeAdapter
import nz.co.test.transactions.services.TransactionsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    private val baseUrl = "https://gist.githubusercontent.com/Josh-Ng/500f2716604dc1e8e2a3c6d31ad01830/raw/4d73acaa7caa1167676445c922835554c5572e82/"

    @Provides
    fun providesTransactionService(retrofit: Retrofit): TransactionsService =
        retrofit.create(TransactionsService::class.java)

    @Provides
    fun provideRetrofitInstance() : Retrofit {

        val moshi = Moshi.Builder()
            .add(BigDecimalAdapter)
            .add(OffsetDateTimeAdapter)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}