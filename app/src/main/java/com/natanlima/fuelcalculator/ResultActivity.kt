package com.natanlima.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val KEY_RESULT_PRICE = "Activity.KEY_PRICE";
const val KEY_RESULT_CONSUMPTION = "Activity.KEY_CONSUMPTION";
const val KEY_RESULT_DISTANCE = "Activity.KEY_DISTANCE";

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.apply {
            statusBarColor = ContextCompat.getColor(this@ResultActivity, R.color.white);
        }

        val price = intent.getFloatExtra(KEY_RESULT_PRICE, 0f);
        val consumption = intent.getFloatExtra(KEY_RESULT_CONSUMPTION, 0f);
        val distance = intent.getFloatExtra(KEY_RESULT_DISTANCE, 0f);
        val finalResult = (distance / consumption) * price;

        val newCalcButton = findViewById<Button>(R.id.btn_new_calc);
        val tvResult = findViewById<TextView>(R.id.tv_result);
        val tvPriceValue = findViewById<TextView>(R.id.price_value);
        val tvConsumptionValue = findViewById<TextView>(R.id.consumption_value);
        val tvDistanceValue = findViewById<TextView>(R.id.km_value);

        tvResult.text = "$${String.format("%.2f", finalResult)}";
        tvPriceValue.text = price.toString();
        tvConsumptionValue.text = consumption.toString();
        tvDistanceValue.text = distance.toString();


        newCalcButton.setOnClickListener {
            val intent = Intent(this, PriceActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP;
            }
            startActivity(intent);
        }
    }
}