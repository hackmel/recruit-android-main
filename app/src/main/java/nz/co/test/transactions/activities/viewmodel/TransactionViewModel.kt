package nz.co.test.transactions.activities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsService
import javax.inject.Inject

class TransactionViewModel @Inject constructor(var transactionService: TransactionsService): ViewModel() {

    private var transactions: MutableLiveData<Array<Transaction>> = MutableLiveData()
    private var selectedTransaction: Transaction? = null

    fun getTransactions (): MutableLiveData<Array<Transaction>> {
        runBlocking {
           val response = transactionService.retrieveTransactions()
            transactions.postValue(response)
        }

        return transactions
    }

    fun selectTransaction (index: Int) {
        selectedTransaction = transactions.value?.get(index)!!
    }

    fun getSelectedTransaction (): Transaction? {
        return selectedTransaction
    }



}