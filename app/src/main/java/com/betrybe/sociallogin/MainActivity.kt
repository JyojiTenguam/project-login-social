package com.betrybe.sociallogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)

        loginButton.isEnabled = false

        emailInput.addTextChangedListener(textWatcher)
        passwordInput.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(string: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(string: CharSequence?, start: Int, before: Int, count: Int) {
            val isEmailFilled = emailInput.text.toString().isNotEmpty()
            val isPasswordFilled = passwordInput.text.toString().isNotEmpty()
            loginButton.isEnabled = isEmailFilled && isPasswordFilled
        }

        override fun afterTextChanged(string: Editable?) {}
    }
}
