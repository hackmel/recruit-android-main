package nz.co.test.transactions.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import nz.co.test.transactions.R
import nz.co.test.transactions.activities.viewmodel.TransactionViewModel

class DetailFragment : Fragment() {

    lateinit var viewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtSummary = view.findViewById<TextView>(R.id.detail_summary)
        val transaction = viewModel.getSelectedTransaction()

        if(transaction != null) {
            txtSummary.text = transaction.summary
        }



    }

}