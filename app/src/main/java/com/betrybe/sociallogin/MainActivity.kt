package com.betrybe.sociallogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailInputLayout = findViewById(R.id.email_text_input_layout)
        passwordInputLayout = findViewById(R.id.password_text_input_layout)
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)

        loginButton.isEnabled = false

        emailInput.addTextChangedListener(textWatcher)
        passwordInput.addTextChangedListener(textWatcher)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (isValidEmail(email) && isValidPassword(password)) {
                emailInputLayout.error = null
                passwordInputLayout.error = null
                Snackbar.make(
                    findViewById(R.id.main),
                    "Login efetuado com sucesso",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                if (!isValidEmail(email)) {
                    emailInputLayout.error = "Email inválido"
                }
                if (!isValidPassword(password)) {
                    passwordInputLayout.error = "Senha deve ter mais de 4 caracteres"
                }
            }
        }
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

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length > 4
    }
}
