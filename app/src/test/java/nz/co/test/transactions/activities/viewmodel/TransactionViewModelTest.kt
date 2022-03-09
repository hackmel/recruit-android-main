package nz.co.test.transactions.activities.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal


@RunWith(MockitoJUnitRunner::class)
class TransactionViewModelTest  {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val service: TransactionsService = Mockito.mock(TransactionsService::class.java)
    private lateinit var viewModel: TransactionViewModel
    private lateinit var transactions : Array<Transaction>

    private var mutableData: MutableLiveData<Array<Transaction>> = MutableLiveData()


     @Before
     fun setup() {
         viewModel = TransactionViewModel(service)
         transactions  =
             arrayOf<Transaction>(
                 Transaction(summary = "Test data 1", credit = BigDecimal(20), debit = BigDecimal(2.5), id = 1, transactionDate = "2022-01-01"),
                 Transaction(summary = "Test data 2", credit = BigDecimal(10), debit = BigDecimal(1.5), id = 1, transactionDate = "2022-01-01"),
                 Transaction(summary = "Test data 3", credit = BigDecimal(0), debit = BigDecimal(3.5), id = 1, transactionDate = "2022-01-01"),
         )
         mutableData.postValue(transactions)
         Mockito.`when`(runBlocking { service.retrieveTransactions() }).thenReturn(transactions)
     }


    @Test
    fun `should return the correct transactions`() {
       val t = viewModel.getTransactions()
        assertEquals(t.value, transactions)
    }

    @Test
    fun `should return the correct number of transactions`() {
        val t = viewModel.getTransactions()
        assertEquals(t.value?.size, transactions.size)
    }

    @Test
    fun `should return the correct selected transaction`() {
        val transactions = viewModel.getTransactions()
        viewModel.selectTransaction(0)
        val selected = viewModel.getSelectedTransaction()

        assertEquals(selected, transactions.value?.get(0))
    }

    @Test
    fun `should return the correct GST_of_the_selected transaction`() {
        viewModel.getTransactions()
        viewModel.selectTransaction(2)
        val selected = viewModel.getSelectedTransaction()

        assertEquals(viewModel.getGSTOfSelectedTransaction(),selected?.debit?.multiply(BigDecimal(.15)))
    }
}



