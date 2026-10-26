package com.example.baitap

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Khai báo biến
    private lateinit var edtMSSV: EditText
    private lateinit var edtHoTen: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvInfo: TextView
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton
    private lateinit var radioGroup: RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtMSSV = findViewById(R.id.edtMSSV)
        edtHoTen = findViewById(R.id.edtHoTen)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        btnRegister = findViewById(R.id.btnRegister)
        tvInfo = findViewById(R.id.tvInfo)
        radioMale = findViewById(R.id.radioMale)
        radioFemale = findViewById(R.id.radioFemale)
        radioGroup = findViewById(R.id.radioGroup)


        edtMSSV.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edtMSSV.text.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập MSSV", Toast.LENGTH_SHORT).show()
            }
        }

        edtHoTen.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edtHoTen.text.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show()
            }
        }

        edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edtEmail.text.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show()
            }
        }

        edtPhone.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edtPhone.text.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            registerStudent()
        }
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val gender = when (checkedId) {
                R.id.radioMale -> "Nam"
                R.id.radioFemale -> "Nữ"
                else -> "Chưa chọn"
            }
            Toast.makeText(this, "Giới tính: $gender", Toast.LENGTH_SHORT).show()
        }

    }


    private fun registerStudent() {
        // Lấy dữ liệu từ EditText
        val mssv = edtMSSV.text.toString().trim()
        val hoTen = edtHoTen.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val phone = edtPhone.text.toString().trim()
        val gender = when (radioGroup.checkedRadioButtonId) {
            R.id.radioMale -> "Nam"
            R.id.radioFemale -> "Nữ"
            else -> "Chưa chọn"
        }

        if (mssv.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập MSSV", Toast.LENGTH_SHORT).show()
            return
        }

        if (hoTen.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show()
            return
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show()
            return
        }

        if (phone.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show()
            return
        }


        val info = """
            THÔNG TIN SINH VIÊN
            
            MSSV: $mssv
            Họ tên: $hoTen
            Email: $email
            Số điện thoại: $phone
        """.trimIndent()

        tvInfo.text = info

        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_LONG).show()
    }
}
