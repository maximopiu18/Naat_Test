package com.naat.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.naat.test.constans.Constantes.Companion.modelDatos
import com.naat.test.model.Datos
import com.naat.test.model.ModelFilterService

class ActivityServices : AppCompatActivity() {
    lateinit var recyclerServices: RecyclerView
    lateinit var recyclerFilter: RecyclerView
    lateinit var adapterFilter: AdapterFilter
    lateinit var adapterServices: AdapterServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)
        recyclerFilter = findViewById(R.id.recycler_busqueda)
        recyclerServices = findViewById(R.id.recycler_servicios)
        initjsonCustom()
        initAdapterFilter()
        initAdapterServices()
    }

    fun initjsonCustom() {
        val gson = GsonBuilder().setLenient().create()
        val inputStream = this.assets.open("json_companias.json")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        modelDatos = Datos()
        modelDatos = gson.fromJson(inputString, Datos::class.java)
    }
    fun initAdapterFilter(){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val myListData: Array<ModelFilterService> = arrayOf<ModelFilterService>(
            ModelFilterService("Todo"),
            ModelFilterService("Recargas"),
            ModelFilterService("Recaudacion"),
            ModelFilterService("Administracion")
        )
        adapterFilter = AdapterFilter(myListData, this)
        //    val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerFilter.setLayoutManager(layoutManager)
        recyclerFilter.setHasFixedSize(true)
        recyclerFilter.setAdapter(adapterFilter)
    }
    fun initAdapterServices(){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapterServices = AdapterServices(modelDatos, this)
       // val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerServices.setLayoutManager(layoutManager)
        recyclerServices.setHasFixedSize(true)
        recyclerServices.setAdapter(adapterServices)
    }
}