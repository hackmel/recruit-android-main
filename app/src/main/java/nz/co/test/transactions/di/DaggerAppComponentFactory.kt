package nz.co.test.transactions.di

import android.app.Activity
import android.content.Intent
import androidx.core.app.AppComponentFactory
import nz.co.test.transactions.activities.MainActivity
import nz.co.test.transactions.activities.ViewModel.TransactionViewModel
import nz.co.test.transactions.services.TransactionsService
import javax.inject.Inject
import javax.inject.Provider

class DaggerAppComponentFactory : AppComponentFactory() {

    private val component = DaggerAppComponent.create()

    init {
        component.inject(this)
    }

    @Inject
    lateinit var map: Map<Class<out Activity>, @JvmSuppressWildcards Provider<Activity>>

    @Inject
    lateinit var transactionService: TransactionsService

    override fun instantiateActivityCompat(
        cl: ClassLoader,
        className: String,
        intent: Intent?
    ): Activity {
        val activityClass = cl.loadClass(className)
        if (activityClass == MainActivity::class.java) {
            val main = MainActivity()
            val viewModel = TransactionViewModel()
            viewModel.transactionService = transactionService
            main.viewModel = viewModel

            return  main
        }
        else return super.instantiateActivityCompat(cl, className, intent)

    }
}