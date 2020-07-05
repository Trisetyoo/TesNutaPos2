package com.example.test2android

class Penjualan: TransactionListItem {
    companion object {
        fun getDummyPenjualan(): ArrayList<Penjualan> {
            var listPenjualan: ArrayList<Penjualan> = ArrayList()

            var p = Penjualan()
            p.Tanggal = "Senin, 5 November 2018"
            p.Pelanggan = "Bapak Alex"
            p.Meja = "2"
            p.Total = 44250.0
            p.Jam = "10:21"
            listPenjualan.add(p)

            p = Penjualan()
            p.Tanggal = "Senin, 5 November 2018"
            p.Driver = "Sukijo"
            p.Pemesan = "Ibu Anis"
            p.Total = 50000.0
            p.Jam = "10:15"
            listPenjualan.add(p)

            p = Penjualan()
            p.Tanggal = "Senin, 5 November 2018"
            p.Pelanggan = "Bapak Sony"
            p.Total = 150000.0
            p.Jam = "10:10"
            listPenjualan.add(p)

            p = Penjualan()
            p.Tanggal = "Minggu, 4 November 2018"
            p.Pelanggan = "Ibu Amel"
            p.Total = 150000.0
            p.Jam = "10:15"
            listPenjualan.add(p)

            return listPenjualan
        }
    }

    var Tanggal: String = ""
    var Total: Double = 0.0
    var Pelanggan: String = ""
    var Meja: String = ""
    var Driver: String = ""
    var Pemesan: String = ""
    var Jam: String = ""

    override val type: Int
        get() = TransactionListItem.TYPE_TRANSACTION
}