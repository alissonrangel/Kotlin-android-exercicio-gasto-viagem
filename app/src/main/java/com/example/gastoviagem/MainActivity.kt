package com.example.gastoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var text_view: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        text_view = findViewById(R.id.text_total_value)

        //MODO 1
        // implementando a interface View.OnClickListener, pode usar o this
        binding.btnCalculate.setOnClickListener(this)

        //MODO 2 COM FUNÇÃO ANÔNIMA
        binding.editPrice.setOnClickListener(object :  View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText( this@MainActivity, "Fui Clicado edit text!!!", Toast.LENGTH_LONG).show()
            }

        })

        //MODO 3 COM LAMBDA
        binding.editDistance.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "Fui Clicado edit text com lambda!!!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    //método chamado pelo setOnClickListener(this)
    override fun onClick(view: View) {
        if (view.id == R.id.btn_calculate ){
            calculate()
        }
    }

    private fun isNotValid(edit: EditText): Boolean{
        return edit.text.toString() == ""
    }

    fun calculate(){
        val distance = binding.editDistance
        val price = binding.editPrice
        val autonomy = binding.editAutonomy

        if (isNotValid(distance) || isNotValid(price) || isNotValid(autonomy)){
            Toast.makeText(this, getString(R.string.validation), Toast.LENGTH_LONG).show()
            return
        } else {
            if(autonomy.text.toString().toFloat() == 0f) {
                Toast.makeText(this, getString(R.string.validation2), Toast.LENGTH_LONG).show()
                return
            }
        }

        val result = (distance.text.toString().toFloat() / autonomy.text.toString().toFloat()) * price.text.toString().toFloat()

        binding.textTotalValue.text = "R$ ${"%.2f".format(result)}"

        Toast.makeText(this, "Fui Clicado!!! $result", Toast.LENGTH_LONG).show()
    }

    fun testclick(view: View) {
        //context -> é a aplicação viva, rodando
        Toast.makeText(this, "Fui Clicado!!!", Toast.LENGTH_LONG).show()
    }

}