package com.example.test2android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.*


class TransactionListAdapter(
    private val context: Context,
    private val transactionListItems: List<TransactionListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var formatRupiah : NumberFormat

    init {
        val localeID = Locale("in", "ID")
        formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        formatRupiah.maximumFractionDigits = 0
    }


    internal inner class TransactionItemViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        var textViewPelanggan: TextView? = null
        var textViewMeja: TextView? = null
        var textViewDriver: TextView? = null
        var textViewPemesan: TextView? = null
        var textViewTotal: TextView? = null
        var textViewJam: TextView? = null

        init {
            textViewPelanggan = itemView!!.findViewById<TextView>(R.id.pelanggan)
            textViewMeja = itemView!!.findViewById<TextView>(R.id.meja)
            textViewDriver = itemView!!.findViewById<TextView>(R.id.driver)
            textViewPemesan = itemView!!.findViewById<TextView>(R.id.pemesan)
            textViewTotal = itemView!!.findViewById<TextView>(R.id.total)
            textViewJam = itemView!!.findViewById<TextView>(R.id.jam)
        }

        fun bind(penjualan: Penjualan) {
            if (penjualan.Pelanggan.isEmpty()) {
                textViewPelanggan?.visibility = View.GONE
            } else {
                textViewPelanggan?.visibility = View.VISIBLE
                textViewPelanggan?.text = "Pelanggan : " + penjualan.Pelanggan
            }

            if (penjualan.Meja.isEmpty()) {
                textViewMeja?.visibility = View.GONE
            } else {
                textViewMeja?.visibility = View.VISIBLE
                textViewMeja?.text = "Meja : " + penjualan.Meja
            }

            if (penjualan.Driver.isEmpty()) {
                textViewDriver?.visibility = View.GONE
            } else {
                textViewDriver?.visibility = View.VISIBLE
                textViewDriver?.text = "Driver : " + penjualan.Driver
            }

            if (penjualan.Pemesan.isEmpty()) {
                textViewPemesan?.visibility = View.GONE
            } else {
                textViewPemesan?.visibility = View.VISIBLE
                textViewPemesan?.text = "Pemesan : " + penjualan.Pemesan
            }

            textViewJam?.text = penjualan.Jam
            textViewTotal?.text = formatRupiah.format(penjualan.Total)
        }
    }

    internal inner class TransactionDateViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        var textViewTanggal: TextView? = null
        var textViewTotal: TextView? = null

        init {
            textViewTanggal = itemView!!.findViewById<TextView>(R.id.header_tanggal)
            textViewTotal = itemView!!.findViewById<TextView>(R.id.header_total)
        }

        fun bind(transactionDate: TransactionDate) {
            textViewTanggal?.text = transactionDate.date
            textViewTotal?.text = formatRupiah.format(transactionDate.total)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            TransactionListItem.TYPE_DATE -> {
                val viewDate =
                    inflater.inflate(R.layout.date_layout_item, parent, false)
                viewHolder = TransactionDateViewHolder(viewDate)
            }
            TransactionListItem.TYPE_TRANSACTION -> {
                val viewTransaction =
                    inflater.inflate(R.layout.transaction_layout_item, parent, false)
                viewHolder = TransactionItemViewHolder(viewTransaction)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (viewHolder.itemViewType) {
            TransactionListItem.TYPE_TRANSACTION -> {
                val penjualan = transactionListItems[position] as Penjualan
                val transactionItemViewHolder =
                    viewHolder as TransactionItemViewHolder
                transactionItemViewHolder.bind(penjualan)
            }
            TransactionListItem.TYPE_DATE -> {
                val transactionDate =
                    transactionListItems[position] as TransactionDate
                val transactionDateViewHolder =
                    viewHolder as TransactionDateViewHolder
                transactionDateViewHolder.bind(transactionDate)
            }
        }
    }

    override fun getItemCount(): Int {
        return transactionListItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return transactionListItems[position].type
    }

    companion object {
        private const val TAG = "Transaction List Adapter"
    }

}