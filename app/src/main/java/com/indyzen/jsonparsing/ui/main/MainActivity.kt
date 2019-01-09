package com.indyzen.jsonparsing.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.indyzen.jsonparsing.R
import com.indyzen.jsonparsing.ui.http.JsonParsingActivity
import com.indyzen.jsonparsing.ui.retrofit.RetrofitActivity
import com.indyzen.jsonparsing.ui.volley.VolleyActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, View.OnClickListener {


    lateinit var mainVM: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainVM = MainVM(this)

        btnHttp.setOnClickListener(this)
        btnVolley.setOnClickListener(this)
        btnRetrofit.setOnClickListener(this)
    }

    override fun httpLibrary() {
        startActivity(Intent(this, JsonParsingActivity::class.java))
    }

    override fun volleyLibrary() {
        startActivity(Intent(this, VolleyActivity::class.java))
    }

    override fun retrofitLibrary() {
        startActivity(Intent(this, RetrofitActivity::class.java))
    }

    override fun onClick(v: View?) {
        when (v) {
            btnHttp -> {
                mainVM.httpLibrary()
            }
            btnVolley -> {
                mainVM.volleyLibrary()
            }
            btnRetrofit -> {
                mainVM.retrofitLibrary()
            }
            else -> {
            }
        }
    }
}
