package com.example.test2android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initClickListener()
    }

    private fun initClickListener() {
        button_uang_masuk.setOnClickListener {
            startActivity(Intent(this, UangMasukActivity::class.java))
        }

        button_data_penjualan.setOnClickListener {
            startActivity(Intent(this, DataPenjualanActivity::class.java))
        }
    }
}
