package com.example.diceroller

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    private val dices = arrayOf("1", "2", "3")
    var numberOfDices: Int = 1

    val diceMap = mapOf(1 to "dice_1", 2 to "dice_2", 3 to "dice_3", 4 to "dice_4", 5 to "dice_5", 6 to "dice_6")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spinner_dices)
        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dices)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                numberOfDices = p2 + 1
                Toast.makeText(
                    applicationContext,
                    "number of dices is $numberOfDices",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice(numberOfDices)
        }
    }

    private fun rollDice(dicesToRoll: Int) {
        val resultTextView1: TextView = findViewById(R.id.dice_output_1)
        val resultTextView2: TextView = findViewById(R.id.dice_output_2)
        val resultTextView3: TextView = findViewById(R.id.dice_output_3)
        val dice1 = Dice(6)
        val dice2 = Dice(6)
        val dice3 = Dice(6)
        val diceImage1: ImageView = findViewById(R.id.first_dice)
        val diceImage2: ImageView = findViewById(R.id.second_dice)
        val diceImage3: ImageView = findViewById(R.id.third_dice)
        var mapValue: String
        when (dicesToRoll) {
            1 -> {
                val diceRoll1 = dice1.roll()
                resultTextView1.text = diceRoll1.toString()
                mapValue = diceMap.get(diceRoll1)!!
                diceImage1.setImageResource(this.resources.getIdentifier(mapValue, "drawable", this.packageName))
            }
            2 -> {
                val diceRoll1 = dice1.roll()
                resultTextView1.text = diceRoll1.toString()
                mapValue = diceMap.get(diceRoll1)!!
                diceImage1.setImageResource(this.resources.getIdentifier(mapValue, "drawable", this.packageName))
                val diceRoll2 = dice2.roll()
                resultTextView2.text = diceRoll2.toString()
                mapValue = diceMap.get(diceRoll2)!!
                diceImage2.setImageResource(this.resources.getIdentifier(mapValue, "drawable", this.packageName))
            }
            3 -> {
                val diceRoll1 = dice1.roll()
                resultTextView1.text = diceRoll1.toString()
                mapValue = diceMap.get(diceRoll1)!!
                diceImage1.setImageResource(this.resources.getIdentifier(mapValue, "drawable", this.packageName))
                val diceRoll2 = dice2.roll()
                resultTextView2.text = diceRoll2.toString()
                mapValue = diceMap.get(diceRoll2)!!
                diceImage2.setImageResource(this.resources.getIdentifier(mapValue, "drawable", this.packageName))
                val diceRoll3 = dice3.roll()
                resultTextView3.text = diceRoll3.toString()
                mapValue = diceMap.get(diceRoll3)!!
                diceImage3.setImageResource(this.resources.getIdentifier(mapValue, "drawable", this.packageName))
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        val accelerometer: TextView = findViewById(R.id.accelerometer_data)
        accelerometer.text = "x = ${event!!.values[0]}\n\n" +
                "y = ${event.values[1]}\n\n" +
                "z = ${event.values[2]}"
        if (event.values[0] >= 10) {
            rollDice(numberOfDices)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}