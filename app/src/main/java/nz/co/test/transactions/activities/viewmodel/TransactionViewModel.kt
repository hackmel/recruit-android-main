package nz.co.test.transactions.activities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsService
import java.math.BigDecimal
import javax.inject.Inject

class TransactionViewModel @Inject constructor(var transactionService: TransactionsService): ViewModel() {

    private var transactions: MutableLiveData<Array<Transaction>> = MutableLiveData()
    private var selectedTransaction: Transaction? = null

    fun getTransactions (): MutableLiveData<Array<Transaction>> {
         viewModelScope.launch {
           val response = transactionService.retrieveTransactions()

            transactions.postValue(
                response
                .sortedWith { lhs, rhs -> if (lhs.summary < rhs.summary) -1 else 0 }.toTypedArray())
        }

        return transactions
    }

    fun selectTransaction (index: Int) {
        selectedTransaction = transactions.value?.get(index)!!
    }

    fun getSelectedTransaction (): Transaction? = selectedTransaction

    fun getGSTOfSelectedTransaction (): BigDecimal? {

        val tax = BigDecimal(.15)
        if (selectedTransaction!!.debit > BigDecimal(0)) {
            return selectedTransaction?.debit?.multiply(tax)
        }

        return selectedTransaction?.credit?.multiply(tax)
    }



}