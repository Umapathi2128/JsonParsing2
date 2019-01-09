package com.indyzen.jsonparsing.repository.networks

import android.util.Log
import java.net.HttpURLConnection
import java.net.URL
import java.io.*


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

//        httpConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
//        httpConnection.requestMethod = "POST"
//        httpConnection.doInput = true
//        httpConnection.instanceFollowRedirects = false
//        httpConnection.connect()
//        val writer = OutputStreamWriter(httpConnection.outputStream, "UTF-8")
//        writer.write(payload)
//        writer.close()

        httpConnection.requestMethod = "GET"
        val conn: InputStream = BufferedInputStream(httpConnection.inputStream)

        response = convertStreamToString(conn)
        return response
    }

    private fun convertStreamToString(iStream: InputStream): String {
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