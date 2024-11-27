package com.natanlima.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class DistanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_distance)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.apply {
            statusBarColor = ContextCompat.getColor(this@DistanceActivity, R.color.white);
        }

        val price = intent.getFloatExtra(KEY_RESULT_PRICE, 0f);
        val consumption = intent.getFloatExtra(KEY_RESULT_CONSUMPTION, 0f);

        val edtDistance = findViewById<TextInputEditText>(R.id.edt_car_distance);
        val backButton = findViewById<ImageView>(R.id.btn_back);
        val nextButton = findViewById<Button>(R.id.btn_next);

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed();
        }

        nextButton.setOnClickListener {
            val distanceStr: String = edtDistance.text.toString();
            if(distanceStr.isNotEmpty()) {
                val distance: Float = distanceStr.toFloat();

                if(distance == 0f) {
                    val snackbar: Snackbar = Snackbar.make(edtDistance, "A distância não pode ser 0!", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(ContextCompat.getColor(this@DistanceActivity, R.color.white))
                    snackbar.setTextColor(ContextCompat.getColor(this@DistanceActivity, R.color.blue));
                    snackbar.show();
                }
                else {
                    val intent = Intent(this, ResultActivity::class.java);
                    intent.putExtra(KEY_RESULT_DISTANCE, distance);
                    intent.putExtra(KEY_RESULT_CONSUMPTION, consumption);
                    intent.putExtra(KEY_RESULT_PRICE, price);
                    startActivity(intent);
                }
            }
            else {
                val snackbar: Snackbar = Snackbar.make(edtDistance, "Preencha todos os campos!", Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(ContextCompat.getColor(this@DistanceActivity, R.color.white));
                snackbar.setTextColor(ContextCompat.getColor(this@DistanceActivity, R.color.blue));
                snackbar.show();
            }
        }
    }
}