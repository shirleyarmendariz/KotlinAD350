package com.hattrick.aformula4success.kotlinad350

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hattrick.aformula4success.kotlinad350.details.ForecastActivityDetails

class MainActivity : AppCompatActivity() {

    private  val forecastRepository = ForecastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEditText: EditText = findViewById(R.id.zipcodeEditText)
        val enterButton: Button = findViewById(R.id.enterButton)

        enterButton.setOnClickListener {
            val zipcode: String = zipcodeEditText.text.toString()

            if (zipcode.length != 5) {
                Toast.makeText(this,R.string.string_error, Toast.LENGTH_SHORT).show()

            } else {
                forecastRepository.loadForecast(zipcode)
            }
        }
        val forecastList: RecyclerView = findViewById(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

       val dailyForecastAdapter = DailyForecastAdapter() {forecast->
           showForecastDetails(forecast)

        }
        forecastList.adapter = dailyForecastAdapter

        val weeklyForecastObserver = Observer<List<DailyForecast>> {forecastItems ->
           dailyForecastAdapter.submitList(forecastItems)


        }

        forecastRepository.weeklyForecast.observe(this,weeklyForecastObserver)

    }
    private fun showForecastDetails(forecast: DailyForecast){
        val  forecastDetailsIntent = Intent (this,ForecastActivityDetails::class.java)
        forecastDetailsIntent.putExtra("key_temp", forecast.temp)
        forecastDetailsIntent.putExtra("key_description", forecast.description)
        startActivity(forecastDetailsIntent)

    }



    }



