package com.naat.test

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class ActivitySplash :AppCompatActivity() {
    private var asyckSplash: AsyckSplash? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        asyckSplash = AsyckSplash()
        asyckSplash!!.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    inner class AsyckSplash : AsyncTask<Any?, Any?, Any?>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(objects: Array<Any?>): Any? {
            try {
                Thread.sleep(1000)
            } catch (e: Exception){

            }
            return false
        }

        override fun onPostExecute(o: Any?) {
            super.onPostExecute(o)
            openLogin()
        }
    }
    fun openLogin(){
        val intent = Intent(this, ActivityLogin::class.java)
        startActivity(intent)
        finish()
    }

}