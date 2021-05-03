package com.felipe.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import com.felipe.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var calcuate = binding.calculateBmiBtn
        calcuate.setOnClickListener {
            var isValid = validateRequiredFields()
            if (isValid) {
                calcuateBMI()
            }
        }
    }

    private fun validateRequiredFields(): Boolean {
        var weight = binding.weightIpt.text.toString()
        var height = binding.heightIpt.text.toString()
        var message = binding.messageTxv

        if (weight.isEmpty()) {
            message.setText("Enter your weight")
            return false
        }
        if (height.isEmpty()) {
            message.setText("Enter your height")
            return false
        }
        return true
    }

    private fun calcuateBMI() {
        var weight = Integer.parseInt(binding.weightIpt.text.toString())
        var height = java.lang.Float.parseFloat(binding.heightIpt.text.toString())
        var message = binding.messageTxv

        var bmi = weight / (height * height)

        val responseBMI = when {
            bmi <= 18.5 -> "Low Weight!"
            bmi <= 24.9 -> "Normal weight!"
            bmi <= 29.9 -> "Overweight!"
            bmi <= 34.9 -> "Obesity - Grade 1!"
            bmi <= 39.9 -> "Obesity - Grade 2!"
            else -> "Morbid obesity!"
        }
        bmi.toString()

        message.setText("BMI: $bmi\n$responseBMI")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflateMenu = menuInflater
        inflateMenu.inflate(R.menu.menu_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.reset_itm -> resetFields()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun resetFields() {
        var weight = binding.weightIpt
        var height = binding.heightIpt
        var message = binding.messageTxv

        weight.setText("")
        height.setText("")
        message.text = ""
    }
}