package com.example.test2android

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_uang_masuk.*
import java.text.NumberFormat
import java.util.*


class UangMasukActivity : AppCompatActivity() {

    private var isEdit = true
    private lateinit var formatRupiah: NumberFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uang_masuk)

        edit_text_masuk_ke.isEnabled = false
        edit_text_masuk_ke.inputType = InputType.TYPE_NULL
        edit_text_masuk_ke.isFocusable = false
        val localeID = Locale("in", "ID")
        formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        formatRupiah.maximumFractionDigits = 0

        edit_text_jumlah.hint = formatRupiah.format(0)
        edit_text_jumlah.setOnFocusChangeListener { _, isFocused ->
            if (!isFocused) {
                val edited = formatRupiah.format(edit_text_jumlah.text.toString().toLong())
                edit_text_jumlah.setText(edited)
            } else {
                edit_text_jumlah.text.clear()
            }
        }

        initClickListener()
    }

    private fun initClickListener() {
        button_edit.setOnClickListener {
            if (isEdit) {
                edit_text_masuk_ke.isEnabled = true
                edit_text_masuk_ke.inputType = InputType.TYPE_CLASS_TEXT
                edit_text_masuk_ke.isFocusable = true
                edit_text_masuk_ke.isFocusableInTouchMode = true
                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                edit_text_masuk_ke.requestFocus()

                button_edit.text = "Save"
                isEdit = false
            } else {
                button_edit.text = "Edit"
                button_edit.isEnabled = false
                edit_text_masuk_ke.inputType = InputType.TYPE_NULL
                edit_text_masuk_ke.isFocusable = false
            }
        }

        button_simpan.setOnClickListener {
            Toast.makeText(applicationContext, "Data tersimpan", Toast.LENGTH_SHORT).show()
            resetValue()
        }

        text_view_kembali.setOnClickListener {
            finish()
        }
    }

    private fun resetValue() {
        edit_text_dari.text.clear()
        edit_text_keterangan.text.clear()
        edit_text_jenis.text.clear()
        edit_text_jumlah.setText(formatRupiah.format(0))
    }
}
