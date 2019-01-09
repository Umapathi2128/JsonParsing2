package com.indyzen.jsonparsing.ui.volley

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.indyzen.jsonparsing.R
import kotlinx.android.synthetic.main.activity_volley.*
import org.json.JSONException
import org.json.JSONObject

class VolleyActivity : AppCompatActivity() {


    val JSON_URL = "https://simplifiedcoding.net/demos/view-flipper/heroes.php"
    lateinit var heroList: ArrayList<VolleyDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley)
        progressbar.visibility = View.VISIBLE

        heroList = ArrayList()


        loadHeroList()
    }

    /**
     * This method for Creating volley requst and response....
     */
    private fun loadHeroList() {
        val stringRequest = StringRequest(
            Request.Method.GET, JSON_URL,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)

                    val heroArray = obj.getJSONArray("heroes")
                    for (i in 0 until heroArray.length()) {
                        val heroObject = heroArray.getJSONObject(i)
                        val hero = VolleyDataModel(heroObject.getString("name"), heroObject.getString("imageurl"))
                        //adding the hero to herolist
                        heroList.add(hero)
                    }
                    val adapter = VolleyAdapter(heroList)
                    val recycler: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
                    volleyRecycler.layoutManager = recycler
                    volleyRecycler.adapter = adapter
                    progressbar.visibility = View.INVISIBLE

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                //displaying the error in toast if occurrs
                Toast.makeText(this@VolleyActivity, error.message, Toast.LENGTH_SHORT).show()
            })
        //creating a request queue
        val requestQueue = Volley.newRequestQueue(this)
        //adding the string request to request queue
        requestQueue.add(stringRequest)
    }
}
