package com.example.registereventapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtHoTen = findViewById<EditText>(R.id.edtHoTen)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val rgGioiTinh = findViewById<RadioGroup>(R.id.rgGioiTinh)
        val cbFacebook = findViewById<CheckBox>(R.id.cbFacebook)
        val cbZalo = findViewById<CheckBox>(R.id.cbZalo)
        val cbTiktok = findViewById<CheckBox>(R.id.cbTiktok)
        val btnDangKy = findViewById<Button>(R.id.btnDangKy)

        // 1. Toast cho EditText khi rời con trỏ chuột ra khỏi ô
        edtHoTen.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edtHoTen.text.isNotEmpty()) {
                Toast.makeText(this, "Đã nhập họ tên: ${edtHoTen.text}", Toast.LENGTH_SHORT).show()
            }
        }

        edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edtEmail.text.isNotEmpty()) {
                Toast.makeText(this, "Đã nhập email: ${edtEmail.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Toast khi click chọn RadioButton Giới tính
        rgGioiTinh.setOnCheckedChangeListener { _, checkedId ->
            val selectedRb = findViewById<RadioButton>(checkedId)
            Toast.makeText(this, "Giới tính đã chọn: ${selectedRb.text}", Toast.LENGTH_SHORT).show()
        }

        // 3. Toast khi thao tác Checkbox Mạng xã hội
        val setupCheckboxToast = { cb: CheckBox ->
            cb.setOnCheckedChangeListener { _, isChecked ->
                val trangThai = if (isChecked) "Chọn" else "Bỏ chọn"
                Toast.makeText(this, "$trangThai: ${cb.text}", Toast.LENGTH_SHORT).show()
            }
        }
        setupCheckboxToast(cbFacebook)
        setupCheckboxToast(cbZalo)
        setupCheckboxToast(cbTiktok)

        // 4. Sự kiện bấm nút Đăng ký -> Chuyển sang DetailActivity
        btnDangKy.setOnClickListener {
            val hoTen = edtHoTen.text.toString().trim()
            val email = edtEmail.text.toString().trim()

            if (hoTen.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ Họ tên và Email!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val checkedGioiTinhId = rgGioiTinh.checkedRadioButtonId
            val gioiTinh = if (checkedGioiTinhId != -1) {
                findViewById<RadioButton>(checkedGioiTinhId).text.toString()
            } else {
                "Chưa chọn"
            }

            val listMXH = mutableListOf<String>()
            if (cbFacebook.isChecked) listMXH.add("Facebook")
            if (cbZalo.isChecked) listMXH.add("Zalo")
            if (cbTiktok.isChecked) listMXH.add("TikTok")
            val mangXaHoi = if (listMXH.isNotEmpty()) listMXH.joinToString(", ") else "Không có"

            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("HO_TEN", hoTen)
                putExtra("EMAIL", email)
                putExtra("GIOI_TINH", gioiTinh)
                putExtra("MANG_XA_HOI", mangXaHoi)
            }
            startActivity(intent)
        }
    }
}