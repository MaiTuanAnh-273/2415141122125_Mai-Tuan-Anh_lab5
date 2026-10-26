package com.example.formvalidation_lec5

data class User(
    val username: String,
    val email: String,
    val password: String,
    val phone: String,
    val fullName: String
) {
    // Hàm hiển thị thông tin
    override fun toString(): String {
        return """
            Tên đăng nhập: $username
            Email: $email
            Mật khẩu: ${"*".repeat(password.length)}
            Số điện thoại: $phone
            Họ tên: $fullName
        """.trimIndent()
    }
}
