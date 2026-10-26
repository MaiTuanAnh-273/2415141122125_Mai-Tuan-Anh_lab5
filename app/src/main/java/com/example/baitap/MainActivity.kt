package com.example.baitap

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtMSSV: EditText
    private lateinit var edtHoTen: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton
    private lateinit var radioGroup: RadioGroup
    private lateinit var checkSports: CheckBox
    private lateinit var checkMusic: CheckBox
    private lateinit var checkReading: CheckBox
    private lateinit var btnRegister: Button
    private lateinit var tvInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các view
        edtMSSV = findViewById(R.id.edtMSSV)
        edtHoTen = findViewById(R.id.edtHoTen)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        radioMale = findViewById(R.id.radioMale)
        radioFemale = findViewById(R.id.radioFemale)
        radioGroup = findViewById(R.id.radioGroup)
        checkSports = findViewById(R.id.checkSports)
        checkMusic = findViewById(R.id.checkMusic)
        checkReading = findViewById(R.id.checkReading)
        btnRegister = findViewById(R.id.btnRegister)
        tvInfo = findViewById(R.id.tvInfo)

        // Event cho EditText
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

        // Event cho RadioButton
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val gender = when (checkedId) {
                R.id.radioMale -> "Nam"
                R.id.radioFemale -> "Nữ"
                else -> "Chưa chọn"
            }
            Toast.makeText(this, "Giới tính: $gender", Toast.LENGTH_SHORT).show()
        }

        // Event cho CheckBox
        checkSports.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Đã chọn: Thể thao", Toast.LENGTH_SHORT).show()
            }
        }

        checkMusic.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Đã chọn: Âm nhạc", Toast.LENGTH_SHORT).show()
            }
        }

        checkReading.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Đã chọn: Đọc sách", Toast.LENGTH_SHORT).show()
            }
        }

        // Event cho Button đăng ký
        btnRegister.setOnClickListener {
            registerStudent()
        }
    }

    private fun registerStudent() {
        val mssv = edtMSSV.text.toString().trim()
        val hoTen = edtHoTen.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val phone = edtPhone.text.toString().trim()

        // Kiểm tra validation
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

        // Lấy giới tính
        val gender = when (radioGroup.checkedRadioButtonId) {
            R.id.radioMale -> "Nam"
            R.id.radioFemale -> "Nữ"
            else -> "Chưa chọn"
        }

        // Lấy sở thích
        val hobbies = mutableListOf<String>()
        if (checkSports.isChecked) hobbies.add("Thể thao")
        if (checkMusic.isChecked) hobbies.add("Âm nhạc")
        if (checkReading.isChecked) hobbies.add("Đọc sách")

        val hobbiesText = if (hobbies.isEmpty()) "Chưa chọn" else hobbies.joinToString(", ")

        // Hiển thị thông tin
        val info = """
            THÔNG TIN SINH VIÊN
            
            MSSV: $mssv
            Họ tên: $hoTen
            Email: $email
            Số điện thoại: $phone
            Giới tính: $gender
            Sở thích: $hobbiesText
        """.trimIndent()

        tvInfo.text = info

        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_LONG).show()
    }
}
