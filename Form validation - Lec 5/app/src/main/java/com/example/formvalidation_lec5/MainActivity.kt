package com.example.formvalidation_lec5

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // Khai báo các View
    private lateinit var edtUsername: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtConfirmPassword: EditText
    private lateinit var edtPhone: EditText
    private lateinit var edtFullName: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvUserInfo: TextView

    private var registeredUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        setupEvents()
    }

    private fun initViews() {
        edtUsername = findViewById(R.id.edtUsername)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword)
        edtPhone = findViewById(R.id.edtPhone)
        edtFullName = findViewById(R.id.edtFullName)
        btnRegister = findViewById(R.id.btnRegister)
        tvUserInfo = findViewById(R.id.tvUserInfo)
    }

    private fun setupEvents() {
        // Sự kiện nút Đăng ký
        btnRegister.setOnClickListener {
            handleRegister()
        }
    }

    private fun handleRegister() {

        val username = edtUsername.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString()
        val confirmPassword = edtConfirmPassword.text.toString()
        val phone = edtPhone.text.toString().trim()
        val fullName = edtFullName.text.toString().trim()

        val validationResult = validateAllFields(
            username, email, password, confirmPassword, phone, fullName
        )
        if (!validationResult.isValid) {
            showErrorSnackbar(validationResult.errorMessage)
            return
        }

        registeredUser = User(username, email, password, phone, fullName)
        showSuccessResult()

        if (!validationResult.isValid) {
            showErrorSnackbar(validationResult.errorMessage)
            return
        }

        Toast.makeText(this, "Validation passed!", Toast.LENGTH_SHORT).show()
    }
    private fun showSuccessResult() {
        Toast.makeText(this, "✓ Đăng ký thành công!", Toast.LENGTH_SHORT).show()

        Snackbar.make(
            findViewById(android.R.id.content),
            "✓ Chào mừng ${registeredUser?.username}!",
            Snackbar.LENGTH_LONG
        ).setBackgroundTint(Color.parseColor("#4CAF50"))
            .setTextColor(Color.WHITE)
            .show()

        tvUserInfo.visibility = android.view.View.VISIBLE
        tvUserInfo.text = """
            ✓ ĐĂNG KÝ THÀNH CÔNG
            
            ${registeredUser.toString()}
            
            Thời gian: ${getCurrentDateTime()}
        """.trimIndent()

        val scrollView = findViewById<ScrollView>(android.R.id.content)
        scrollView.post {
            scrollView.fullScroll(ScrollView.FOCUS_DOWN)
        }
    }

    private fun getCurrentDateTime(): String {
        val sdf = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss", java.util.Locale.getDefault())
        return sdf.format(java.util.Date())
    }

    private fun validateAllFields(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String,
        fullName: String
    ): ValidationResult {

        if (username.isEmpty()) {
            return ValidationResult(false, "❌ Tên đăng nhập không được để trống")
        }
        if (username.length < 3 || username.length > 20) {
            return ValidationResult(false, "❌ Tên đăng nhập phải từ 3-20 ký tự")
        }
        if (!username.matches(Regex("^[a-zA-Z0-9_]+$"))) {
            return ValidationResult(false, "❌ Tên đăng nhập chỉ chứa chữ, số và dấu gạch dưới")
        }

        if (email.isEmpty()) {
            return ValidationResult(false, "❌ Email không được để trống")
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(false, "❌ Email không hợp lệ")
        }

        if (password.isEmpty()) {
            return ValidationResult(false, "❌ Mật khẩu không được để trống")
        }
        if (password.length < 6) {
            return ValidationResult(false, "❌ Mật khẩu phải có ít nhất 6 ký tự")
        }

        if (confirmPassword.isEmpty()) {
            return ValidationResult(false, "❌ Vui lòng xác nhận mật khẩu")
        }
        if (password != confirmPassword) {
            return ValidationResult(false, "❌ Mật khẩu xác nhận không khớp")
        }

        if (phone.isEmpty()) {
            return ValidationResult(false, "❌ Số điện thoại không được để trống")
        }
        if (!phone.matches(Regex("^0\\d{9}$"))) {
            return ValidationResult(false, "❌ Số điện thoại phải có 10 số và bắt đầu bằng 0")
        }

        if (fullName.isEmpty()) {
            return ValidationResult(false, "❌ Họ tên không được để trống")
        }
        if (fullName.length < 2) {
            return ValidationResult(false, "❌ Họ tên phải có ít nhất 2 ký tự")
        }

        return ValidationResult(true, "")
    }
    private fun showErrorSnackbar(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).setBackgroundTint(Color.parseColor("#F44336"))
            .setTextColor(Color.WHITE)
            .setActionTextColor(Color.YELLOW)
            .setAction("OK") { /* Đóng Snackbar */ }
            .show()
    }

    data class ValidationResult(
        val isValid: Boolean,
        val errorMessage: String
    )
}
