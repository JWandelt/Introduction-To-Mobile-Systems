package com.example.squareroots

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
            calculateSquareRoots()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateSquareRoots() {
        val quadraticCoefficient: EditText = findViewById(R.id.quadraticInput)
        val linearCoefficient: EditText = findViewById(R.id.linearInput)
        val constantCoefficient: EditText = findViewById(R.id.constantInput)
        val outputView: TextView = findViewById(R.id.outputWindow)

        val square: Double
        val linear: Double
        val constant: Double

        if (quadraticCoefficient.text.isNotEmpty()) {
            square = quadraticCoefficient.text.toString().toDouble()
            linear = linearCoefficient.text.toString().toDouble()
            constant = constantCoefficient.text.toString().toDouble()
        } else {
            Toast.makeText(this, "Fill the remaining fields first!", Toast.LENGTH_SHORT).show()
            return
        }

        val root1: Double
        val root2: Double

        if (square == 0.0)
            Toast.makeText(this, "This is a linear equation, not quadratic!", Toast.LENGTH_SHORT)
                .show()
        else {
            val delta = linear.pow(2.0) - (4 * square * constant)

            when {
                delta == 0.0 -> {
                    root1 = (-linear + sqrt(delta)) / (2 * square)

                    outputView.text = "The square root is: $root1"
                }
                delta < 0.0 -> {
                    Toast.makeText(
                        this,
                        "Delta is negative, hence the square roots can't be calculated",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    root1 = (-linear + sqrt(delta)) / (2 * square)
                    root2 = (-linear - sqrt(delta)) / (2 * square)

                    outputView.text = "The square roots are: $root1 and $root2"
                }
            }
        }
    }
}