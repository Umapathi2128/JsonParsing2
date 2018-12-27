package com.indyzen.jsonparsing.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.indyzen.jsonparsing.R

class MainActivity : AppCompatActivity(),MainView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun httpLibrary() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun volleyLibrary() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrofitLibrary() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
