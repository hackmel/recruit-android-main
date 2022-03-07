package nz.co.test.transactions.activities.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionViewModel(): ViewModel() {

    lateinit var transactionService: TransactionsService
    private var transactions: MutableLiveData<Array<Transaction>> = MutableLiveData()
    private var selectedTransaction: Transaction? = null

    fun getTransactions (): MutableLiveData<Array<Transaction>> {
        return transactions
    }

    fun selectTransaction (index: Int) {
        selectedTransaction = transactions.value?.get(index)!!
    }

    fun getSelectedTransaction (): Transaction? {
        return selectedTransaction
    }

    fun fetchTransactions()  {

            var call : Call<Array<Transaction>>? =  transactionService.retrieveTransactions()

            call?.enqueue(object: Callback<Array<Transaction>> {
                override fun onFailure(call: Call<Array<Transaction>>, t: Throwable) {
                    Log.e("MainActivity", t.message.toString())
                }

                override fun onResponse(
                    call: Call<Array<Transaction>>,
                    response: Response<Array<Transaction>>
                ) {
                    Log.i("MainActivity", response.message().toString())

                    transactions.postValue(response.body())

                }
            })
        }



}