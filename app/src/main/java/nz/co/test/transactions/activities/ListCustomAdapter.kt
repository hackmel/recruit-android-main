package nz.co.test.transactions.activities

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.R
import nz.co.test.transactions.services.Transaction
import java.math.BigDecimal

internal class ListCustomAdapter  :
    RecyclerView.Adapter<ListCustomAdapter.ViewHolder>() {

    lateinit var transactions: Array<Transaction>
    lateinit var onclickEventListener: ItemOnClickListener

    internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val summary: TextView = view.findViewById(R.id.summary)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = transactions[position]
        var itemRowColor = "#6EBF8B"

        holder.summary.text = item.summary

        if(item.credit > BigDecimal(0) ){
            itemRowColor = "#D82148"
        }

        holder.itemView.setBackgroundColor(Color.parseColor(itemRowColor))

        holder.itemView.setOnClickListener{
            val pos = holder.adapterPosition
            onclickEventListener.onClick(holder.itemView, pos)
        }
    }

    override fun getItemCount(): Int = transactions.size
}

