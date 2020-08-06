package com.naat.test

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ActivityPagarServicio : AppCompatActivity(){
    lateinit var btnContinuar:Button
    lateinit var alertDialog: AlertDialog
    lateinit var edNumero: EditText
    lateinit var edCantidad: EditText
    lateinit var imgBack : ImageView
    lateinit var tvTittle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
        tvTittle = findViewById(R.id.tittle)
        btnContinuar =findViewById(R.id.activity_pagar_servicio_btn_continuar) as Button
        edNumero = findViewById(R.id.ed_numero) as EditText
        edCantidad = findViewById(R.id.ed_cantidad) as EditText
        imgBack = findViewById(R.id.img_back) as ImageView
        if(intent.hasExtra("pago")){
            var a = intent.getStringExtra("pago")
            tvTittle.setText(""+ a)
        }

        btnContinuar.setOnClickListener {
            if (edNumero.text.isEmpty() ||edNumero.text.length<10){
                Toast.makeText(this, "Ingresa numero de telefono", Toast.LENGTH_SHORT).show()
            }
            else{
                if (edCantidad.text.isEmpty()|| edCantidad.text.length<2){
                    Toast.makeText(this, "Ingresa la cantidad" , Toast.LENGTH_SHORT).show()
                }
                else{
                    dialogResponseServer()
                }
            }
        }
        imgBack.setOnClickListener {
            finish()
        }
    }

    fun dialogResponseServer() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.alertdialog_servicio, null)
        var tvNumero = dialogView.findViewById<View>(R.id.alertdialog_number) as TextView
        var tvCantidad= dialogView.findViewById<View>(R.id.alertdialog_cantidad) as TextView

        var btnAceptar = dialogView.findViewById<View>(R.id.alertdialog_aceptar) as Button
        var btnCancelar = dialogView.findViewById<View>(R.id.alertdialog_cancelar) as Button

        dialogBuilder.setView(dialogView)
        alertDialog = dialogBuilder.create()
        dialogBuilder.setCancelable(false)
        alertDialog.setCancelable(false)
        alertDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
        tvCantidad.setText("$ "+edCantidad.text)
        tvNumero.setText("" +edNumero.text)
        btnCancelar.setOnClickListener {
            alertDialog.dismiss()
        }
        btnAceptar.setOnClickListener {
            alertDialog.dismiss()
            finish()
        }
    }
}