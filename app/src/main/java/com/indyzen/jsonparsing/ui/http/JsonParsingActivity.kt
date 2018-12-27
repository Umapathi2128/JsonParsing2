package com.indyzen.jsonparsing.ui.http

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.indyzen.jsonparsing.R
import com.indyzen.jsonparsing.repository.networks.HttpHandler
import com.indyzen.jsonparsing.ui.DataAdapter
import com.indyzen.jsonparsing.ui.DataModel
import kotlinx.android.synthetic.main.json_parsing_activity.*
import org.json.JSONObject

class JsonParsingActivity : AppCompatActivity() {

    lateinit var list: ArrayList<DataModel>
    lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.json_parsing_activity)

        ctx = this

        list = ArrayList()

        GetContacts().execute()
    }

    @SuppressLint("StaticFieldLeak")
    private inner class GetContacts : AsyncTask<Void, Void, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Toast.makeText(
                this@JsonParsingActivity, "Json Data is " +
                        "downloading", Toast.LENGTH_LONG
            ).show()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            val sh = HttpHandler()
            val url = "https://api.androidhive.info/contacts/"
            val jSonString = sh.makeServiceCall(url)

//            Log.e("Response Url", jSonString)
//            Log.e("Response Url", jSonString.substring(jSonString.indexOf("{"), jSonString.lastIndexOf("}") + 1))
            if (jSonString != null) {

                try {
//                    val jsonParser=JSONParser()
                    val jsonObject = JSONObject(jSonString)
                    val jsonArray = jsonObject.getJSONArray("contacts")

                    for (i in 0 until (jsonArray.length() - 1)) {
                        val c = jsonArray.getJSONObject(i)
                        Log.e("data", c.toString())
                        val phone = c.getJSONObject("phone")
                        list.add(
                            DataModel(
                                c.getString("id"), c.getString("name"), c.getString("email"),
                                c.getString("address"), c.getString("gender"), phone.getString("mobile"),
                                phone.getString("home"), phone.getString("office")
                            )
                        )
                    }
                } catch (e: Exception) {
                    Log.e("Json Exception", e.toString())
                }
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            Toast.makeText(this@JsonParsingActivity, "Json Data is downloaded", Toast.LENGTH_LONG).show()

//            mainAdapter = MainAdapter(list, this)
//            val recyclerView: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
//            activityMainBinding.recyclerview.layoutManager = recyclerView
//            activityMainBinding.recyclerview.itemAnimator = DefaultItemAnimator()
//            activityMainBinding.recyclerview.adapter = mainAdapter
//            activityMainBinding.recyclerview.smoothScrollToPosition(list.size)

            val dataAdapter = DataAdapter(list)
            val recycler: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
            recyclerview.layoutManager = recycler
            recyclerview.adapter = dataAdapter

        }
    }

}
