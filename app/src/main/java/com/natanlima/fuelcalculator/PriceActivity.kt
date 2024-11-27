package com.natanlima.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class PriceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_price)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.apply {
            statusBarColor = ContextCompat.getColor(this@PriceActivity, R.color.white);
        }

        val edtPrice = findViewById<TextInputEditText>(R.id.edt_distance);
        val backButton = findViewById<ImageView>(R.id.btn_back);
        val nextButton = findViewById<Button>(R.id.btn_next);



        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed();
        }

        nextButton.setOnClickListener {
            val priceStr: String = edtPrice.text.toString();
            if(priceStr.isNotEmpty()) {
                val price: Float = priceStr.toFloat();

                if(price == 0f) {
                    val snackbar: Snackbar = Snackbar.make(edtPrice, "O preço não pode ser 0!", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(ContextCompat.getColor(this@PriceActivity, R.color.white))
                    snackbar.setTextColor(ContextCompat.getColor(this@PriceActivity, R.color.blue));
                    snackbar.show();
                }
                else {
                    val intent = Intent(this, ConsumptionActivity::class.java);
                    intent.putExtra(KEY_RESULT_PRICE, price);
                    startActivity(intent);
                }
            }
            else {
                val snackbar: Snackbar = Snackbar.make(edtPrice, "Preencha todos os campos!", Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(ContextCompat.getColor(this@PriceActivity, R.color.white))
                snackbar.setTextColor(ContextCompat.getColor(this@PriceActivity, R.color.blue));
                snackbar.show();

            }
        }
    }
}