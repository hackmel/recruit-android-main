package nz.co.test.transactions.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.R
import nz.co.test.transactions.activities.customAdapter.ListCustomAdapter
import nz.co.test.transactions.activities.ViewModel.TransactionViewModel
import nz.co.test.transactions.activities.fragments.DetailFragment
import nz.co.test.transactions.services.Transaction


class MainActivity : AppCompatActivity(), ItemOnClickListener {

    private lateinit var customAdapter: ListCustomAdapter
    lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(applicationContext)

        recyclerView.layoutManager = layoutManager
        customAdapter = ListCustomAdapter()
        customAdapter.onclickEventListener = this
        customAdapter.transactions = emptyArray<Transaction>()
        recyclerView.adapter = customAdapter


         viewModel.getTransactions().observe( this, Observer<Array<Transaction>> {
             transactions ->
                 customAdapter.transactions = transactions
                 customAdapter.notifyDataSetChanged()
         })

        viewModel.fetchTransactions()
    }

    override fun onClick(view: View, position: Int) {

        val fragment = DetailFragment()
        fragment.viewModel = viewModel

        val activity = view!!.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.main_activity, fragment)
                addToBackStack(null)
                commit()
            }

        viewModel.selectTransaction(position)
    }

}