package com.example.diceroller

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var sensorManager: SensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val accelerometer: TextView = findViewById(R.id.accelerometer_data)
        accelerometer.text = "x = ${event!!.values[0]}\n\n" +
                "y = ${event.values[1]}\n\n" +
                "z = ${event.values[2]}"
        if (event.values[0] >= 10) {
            rollDice()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}