package com.betrybe.sociallogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        emailInputLayout = findViewById(R.id.email_text_input_layout)
        passwordTextInputLayout = findViewById(R.id.password_text_input_layout)

        loginButton.isEnabled = false

        emailInput.addTextChangedListener(textWatcher)
        passwordInput.addTextChangedListener(textWatcher)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Valida o email
            if (isValidEmail(email)) {
                emailInputLayout.error = null // Limpa qualquer erro anterior

                // Valida a senha
                if (password.length > 4) {
                    passwordTextInputLayout.error = null // Limpa qualquer erro anterior
                    Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    passwordTextInputLayout.error = "Senha deve ter mais de 4 caracteres"
                }
            } else {
                emailInputLayout.error = "Email inválido"
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
        val emailPattern = "[a-zA-Z0-9.]+@[a-zA-Z]+\\.[a-zA-Z]+"
        return email.matches(emailPattern.toRegex())
    }
}
