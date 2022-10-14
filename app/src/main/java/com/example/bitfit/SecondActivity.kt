package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val foodNameView = findViewById<EditText>(R.id.foodNameInput)
        val caloriesNumView = findViewById<EditText>(R.id.caloriesNumInput)
        var tempFood : FoodEntity
        val foods = intent.getSerializableExtra("FOOD_LIST") as ArrayList<FoodEntity>
        val button = findViewById<Button>(R.id.recordButton)
        button.setOnClickListener{
            val inputFoodName = foodNameView.text.toString()
            val inputCaloriesNum = caloriesNumView.text.toString()
            tempFood = FoodEntity(inputFoodName, inputCaloriesNum)
            foods.add(tempFood)
            lifecycleScope.launch(IO) {
                (application as FoodApplication).db.foodDao().insert(
                    FoodEntity(inputFoodName, inputCaloriesNum)
                )
            }
            foodNameView.text.clear()
            caloriesNumView.text.clear()
            finish()
        }
    }
}