package com.example.test2android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_data_penjualan.*
import java.text.SimpleDateFormat
import java.util.*


class DataPenjualanActivity : AppCompatActivity() {

    private lateinit var transactionListAdapter: TransactionListAdapter
    val transactionListItems: MutableList<TransactionListItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_penjualan)

        val cal = Calendar.getInstance()
        val simpleFormat = SimpleDateFormat("d MMMM yyyy")
        text_view_tanggal.text = simpleFormat.format(cal.time)

        transactionListItems.addAll(getAllTransactionItems())

        transactionListAdapter = TransactionListAdapter(this, transactionListItems)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerview.adapter = transactionListAdapter

        initClickListener()
    }

    private fun initClickListener() {
        image_date.setOnClickListener {
            val builder = MaterialDatePicker.Builder.dateRangePicker()
            val picker = builder.build()

            picker.addOnPositiveButtonClickListener {
                val timeZoneUTC: TimeZone = TimeZone.getDefault()
                val offsetFromUTC: Int = timeZoneUTC.getOffset(Date().getTime()) * -1

                // Create a date format, then a date object with our offset
                val simpleFormat = SimpleDateFormat("d MMMM yyyy")
                val dateFirst = Date(it.first!!.plus(offsetFromUTC))
                val dateSecond = Date(it.second!!.plus(offsetFromUTC))

                val tanggal = simpleFormat.format(dateFirst) + " - " + simpleFormat.format(dateSecond)
                text_view_tanggal.text = tanggal

                transactionListItems.clear()
                transactionListItems.addAll(getAllTransactionItems())
                transactionListAdapter.notifyDataSetChanged()
            }
            picker.show(supportFragmentManager, "DatePickerDialog")
        }
    }

    fun getAllTransactionItems(): List<TransactionListItem> {
        val listPenjualan: List<Penjualan> =
            Penjualan.getDummyPenjualan()
        val transactionListItems: MutableList<TransactionListItem> = ArrayList()
        var date = ""
        var total = 0.0
        var transactionDate: TransactionDate? = null

        for (penjualan in listPenjualan) {
            val dateCursor: String = penjualan.Tanggal

            if (!date.equals(dateCursor, ignoreCase = true)) {
                transactionDate = TransactionDate(dateCursor)
                transactionListItems.add(transactionDate)
                date = dateCursor
                total = 0.0
                transactionListItems.add(penjualan)
            } else {
                transactionListItems.add(penjualan)

                if (transactionDate != null && transactionListItems.contains(transactionDate)) {
                    transactionListItems.set(transactionListItems.indexOf(transactionDate), transactionDate)
                }
            }

            total += penjualan.Total
            transactionDate?.total = total
        }
        return transactionListItems
    }
}
