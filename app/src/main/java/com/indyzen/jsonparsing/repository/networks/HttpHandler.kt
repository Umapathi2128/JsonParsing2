package com.indyzen.jsonparsing.repository.networks

import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Umapathi on 24/12/18.
 * Copyright Indyzen Inc, @2018
 */
class HttpHandler {

    lateinit var response: String

    fun makeServiceCall(reqUrl: String): String {
        val url = URL(reqUrl)
        val httpConnection = url.openConnection() as HttpURLConnection
//        val con=url.openConnection()
        httpConnection.requestMethod = "GET"
        val conn: InputStream = BufferedInputStream(httpConnection.inputStream)

        response = convertStreamToString(conn)
        return response
    }

    fun convertStreamToString(iStream: InputStream): String {
        val bReader = BufferedReader(InputStreamReader(iStream, "UTF-8"))
        val sBuilder = StringBuilder()
        var line:String=""
        try {
            var line = bReader.readLine()
            while (line != null) {
                sBuilder.append(line + "\n")
                line = bReader.readLine()
            }
        } catch (e: Exception) {
            Log.e("exception", e.toString())
        }finally {
            bReader.close()
        }
        return sBuilder.toString()
    }
}