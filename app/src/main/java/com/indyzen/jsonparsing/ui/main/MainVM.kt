package com.indyzen.jsonparsing.ui.main

import android.content.Context

/**
 * Created by Umapathi on 26/12/18.
 * Copyright Indyzen Inc, @2018
 */
class MainVM(var mainView: MainView) {

    fun httpLibrary(){mainView.httpLibrary()}
    fun volleyLibrary(){mainView.volleyLibrary()}
    fun retrofitLibrary(){mainView.retrofitLibrary()}
}