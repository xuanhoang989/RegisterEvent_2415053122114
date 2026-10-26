package com.example.registereventapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvKetQua = findViewById<TextView>(R.id.tvKetQua)
        val btnQuayLai = findViewById<Button>(R.id.btnQuayLai)

        val hoTen = intent.getStringExtra("HO_TEN") ?: ""
        val email = intent.getStringExtra("EMAIL") ?: ""
        val gioiTinh = intent.getStringExtra("GIOI_TINH") ?: ""
        val mangXaHoi = intent.getStringExtra("MANG_XA_HOI") ?: ""

        tvKetQua.text = """
            Họ và tên: $hoTen
            Email: $email
            Giới tính: $gioiTinh
            Mạng xã hội: $mangXaHoi
        """.trimIndent()

        btnQuayLai.setOnClickListener {
            finish()
        }
    }
}