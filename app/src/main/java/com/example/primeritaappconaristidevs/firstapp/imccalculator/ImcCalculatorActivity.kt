package com.example.primeritaappconaristidevs.firstapp.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.primeritaappconaristidevs.R
import com.example.primeritaappconaristidevs.firstapp.MenuActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false
    private var currentWeight: Int = 60
    private var currentAge: Int = 25
    private var currentHeight: Int = 120

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var rsHeight:RangeSlider
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnCalculate:Button
    private lateinit var btnVolver:Button

    companion object{//Declaro una constante dentro del companion object, accesible desde otras clases
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubstractAge = findViewById(R.id.btnSubstractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
        btnVolver = findViewById(R.id.btnVolver)
    }

    private fun initListeners() {
        viewMale .setOnClickListener{
            changeGender()
            setGenderColor()
        }

        viewFemale .setOnClickListener{
            changeGender()
            setGenderColor()
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight =  df.format(value).toInt()
             tvHeight.text = "$currentHeight cm"
        }

        btnPlusWeight.setOnClickListener(){
            currentWeight+=1
            setWeight()
        }

        btnSubstractWeight.setOnClickListener(){
            currentWeight-=1
            setWeight()
        }

        btnPlusAge.setOnClickListener(){
            currentAge+=1
            setAge()
        }

        btnSubstractAge.setOnClickListener(){
            currentAge-=1
            setAge()
        }

        btnCalculate.setOnClickListener(){
            val result = calculateIMC()
            navigateToResult(result)
        }
        btnVolver.setOnClickListener{
            val intent = Intent (this, MenuActivity::class.java )
            startActivity(intent)
        }

    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df= DecimalFormat("#.##")
        val imc = currentWeight/((currentHeight.toDouble()/100) * (currentHeight.toDouble()/100))
        return  df.format(imc).toDouble()
        //Log.i("AppIMC", "el imc es $imc")
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        getBackgroundColor(isMaleSelected)
        getBackgroundColor(isFemaleSelected)
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent : Boolean): Int {

        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI(){
        setGenderColor()
        setWeight()
        setAge()
    }

}