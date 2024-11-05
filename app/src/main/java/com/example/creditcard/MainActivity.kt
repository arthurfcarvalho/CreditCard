package com.example.creditcard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var cardNumberEditText: EditText
    private lateinit var expiryDateEditText: EditText
    private lateinit var cvvEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cardNumberEditText = findViewById(R.id.editCreditCardNumber)
        expiryDateEditText = findViewById(R.id.editMonthAndYear)
        cvvEditText = findViewById(R.id.editCVV)

        // Formatação automática do número do cartão
        cardNumberEditText.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return
                isUpdating = true

                val input = s.toString().replace(" ", "")
                val formatted = StringBuilder()
                for (i in input.indices) {
                    if (i > 0 && i % 4 == 0) formatted.append(" ")
                    formatted.append(input[i])
                }

                cardNumberEditText.setText(formatted.toString())
                cardNumberEditText.setSelection(formatted.length)
                isUpdating = false
            }
        })

        // Validação e formatação da data de validade
        expiryDateEditText.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return
                isUpdating = true

                val input = s.toString().replace("/", "")
                val calendar = Calendar.getInstance()
                val currentYear = calendar.get(Calendar.YEAR) % 100 // Ano atual em formato de dois dígitos (ex: 2023 -> 23)

                // Formatação para MM/AA
                val formattedDate = when {
                    input.length >= 2 -> "${input.substring(0, 2)}/${input.substring(2)}"
                    else -> input
                }

                expiryDateEditText.setText(formattedDate)
                expiryDateEditText.setSelection(formattedDate.length)
                isUpdating = false

                // Validação do mês e ano
                if (input.length == 4) {
                    val month = input.substring(0, 2).toIntOrNull()
                    val year = input.substring(2).toIntOrNull()

                    if (month == null || month < 1 || month > 12) {
                        expiryDateEditText.error = "Mês inválido"
                    } else if (year == null || year < currentYear) {
                        expiryDateEditText.error = "Ano inválido"
                    }
                }
            }
        })
    }

    // Função de validação
    private fun validateFields(): Boolean {
        val cardNumber = cardNumberEditText.text.toString().replace(" ", "")
        val expiryDate = expiryDateEditText.text.toString()
        val cvv = cvvEditText.text.toString()

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR) % 100 // Ano atual em dois dígitos (ex: 2023 -> 23)

        // Verifica mês e ano de validade
        val monthYear = expiryDate.replace("/", "")
        val month = monthYear.substring(0, 2).toIntOrNull()
        val year = monthYear.substring(2).toIntOrNull()

        return when {
            cardNumber.length != 16 -> {
                Toast.makeText(this, "Número do cartão inválido", Toast.LENGTH_SHORT).show()
                false
            }
            month == null || month < 1 || month > 12 -> {
                Toast.makeText(this, "Mês de validade inválido", Toast.LENGTH_SHORT).show()
                false
            }
            year == null || year < currentYear -> {
                Toast.makeText(this, "Ano de validade inválido", Toast.LENGTH_SHORT).show()
                false
            }
            cvv.length != 3 -> {
                Toast.makeText(this, "CVV inválido", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }
}
