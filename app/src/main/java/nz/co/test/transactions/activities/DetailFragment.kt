package nz.co.test.transactions.activities

import android.icu.text.DecimalFormat
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import nz.co.test.transactions.R
import nz.co.test.transactions.activities.viewmodel.TransactionViewModel
import java.time.format.DateTimeFormatter

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
        val txtDebit = view.findViewById<TextView>(R.id.detail_debit)
        val txtCredit = view.findViewById<TextView>(R.id.detail_credit)
        val txtTransactionDate = view.findViewById<TextView>(R.id.detail_transaction_date)
        val txtGST = view.findViewById<TextView>(R.id.detail_gst)

        val transaction = viewModel.getSelectedTransaction()

        transaction.let {

            val decimalFormat = DecimalFormat("#.##")

            txtSummary.text = transaction?.summary
            txtDebit.text = transaction?.debit.toString()
            txtCredit.text = transaction?.credit.toString()
            txtTransactionDate.text = transaction?.transactionDate
            txtGST.text = decimalFormat.format(viewModel.getGSTOfSelectedTransaction())
        }



    }

}