package com.indyzen.jsonparsing.ui.retrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.indyzen.jsonparsing.R
import com.indyzen.jsonparsing.repository.networks.retrofit.RetrofitAPI
import com.indyzen.jsonparsing.repository.networks.retrofit.RetrofitConfig
import com.indyzen.numberplatedetection.repository.networks.ServiceResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        login()
    }

    private fun login() {
        val service :RetrofitAPI?= RetrofitConfig()
            .getRetrofit()?.create(RetrofitAPI::class.java)

//        service?.login1("sumonsen@innovination.com", "123456")
//            ?.enqueue(object: retrofit2.Callback<ServiceResponse> {
//                override fun onFailure(call: Call<ServiceResponse>?, t: Throwable?) {
//                }
//
//                override fun onResponse(call: Call<ServiceResponse>?, response: Response<ServiceResponse>?) {
//                response?.errorBody()
//                }
//            })

        service?.login("sumonsen@innovination.com", "123456")?.subscribeOn(Schedulers.io())
//        service?.login(email, password)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Subscriber<ServiceResponse>() {
                override fun onNext(t: ServiceResponse?) {
                    Log.e("data", t!!.toString())
                }

                override fun onCompleted() {
                    Log.e("onCompleted", "Success")
                    Toast.makeText(this@RetrofitActivity, "Success...", Toast.LENGTH_LONG).show()

//                    startActivity(Intent(this, MainActivity::class.java))
//                    finish()
                }

                override fun onError(e: Throwable?) {
                    Log.e("onError", e.toString())
                    Toast.makeText(this@RetrofitActivity, "Invalid UserName or Password...", Toast.LENGTH_LONG).show()
                }
            })
    }
}
