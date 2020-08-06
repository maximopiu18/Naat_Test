package com.naat.test

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.*
import com.google.gson.Gson
import com.naat.test.constans.Constantes.Companion.responseToken
import com.naat.test.model.ResponseToken
import com.naat.test.provider.Preferencias


class ActivityLogin : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    lateinit var btnLogin: Button
    lateinit var edUsuario: EditText
    lateinit var edPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edUsuario = findViewById(R.id.ed_user) as EditText
        edPass = findViewById(R.id.ed_pass) as EditText
        changeSharePreferneces()

        btnLogin = findViewById(R.id.activity_login_btn_iniciar_sesion) as Button
        btnLogin.setOnClickListener {
            if (edUsuario.text.isEmpty()) {
                Toast.makeText(this, "Ingresa el usuario", Toast.LENGTH_SHORT).show()
            } else {
                if (edPass.text.isEmpty()) {
                    Toast.makeText(this, "Ingresa la contraseña", Toast.LENGTH_SHORT).show()
                } else {
                    restToken()
                }
            }

        }

    }

    fun restToken() {

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Cargando...")
        progressDialog.setMessage("Espere un momento por favor...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(false)
        progressDialog.show()


        var url = "https://uat.firmaautografa.com/authorization-server/oauth/token"
        val queue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> {
                val gson = Gson()
                responseToken = ResponseToken()
                responseToken = gson.fromJson(it, ResponseToken::class.java)
                if (progressDialog.isShowing) {
                    progressDialog.cancel()
                    saveSharePreferences()
                    openServices()
                }


            },
            Response.ErrorListener {
                Log.e("error", "error: " + it)
                if (progressDialog.isShowing) {
                    progressDialog.cancel()
                }
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/x-www-form-urlencoded"
                headers["Authorization"] =
                    "Basic Wm1Ga0xXTXlZeTF3YjNKMFlXdz06TWpoa04yUTNNbUppWVRWbVpHTTBObVl4Wmpka1lXSmpZbVEyTmpBMVpEVXpaVFZoT1dNMVpHVTROakF4TldVeE9EWmtaV0ZpTnpNd1lUUm1ZelV5WWc9PQ=="
                return headers
            }

            override fun getParams(): Map<String, String> {
                val pars: MutableMap<String, String> = HashMap()
                pars["grant_type"] = "password"
                pars["username"] = "testnaat@na-at.com.mx"
                pars["password"] =
                    "a0700af71a183b82aa4d79682475b151161bf91138d77f6f10937240f40814bd"
                return pars
            }
        }

        queue.add(stringRequest)

    }

    fun openServices() {
        var intent = Intent(this, ActivityServices::class.java)
        startActivity(intent)
        finish()
    }


    fun changeSharePreferneces() {
        var preferencias = Preferencias()
        edUsuario.setText(preferencias.getSaveLoginPass(this).toString())
        edPass.setText(preferencias.getSaveLoginPass(this).toString())
    }

    fun saveSharePreferences(){
        var preferencias = Preferencias()
        preferencias.setSaveLogin(this, edUsuario.text.toString(), edPass.text.toString())
    }



}