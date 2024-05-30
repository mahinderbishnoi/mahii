package com.example.apiintegration

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Getdata : AppCompatActivity() {
    private var mRequestQueue: RequestQueue? = null
    private var mStringRequest: StringRequest? = null
    private val url = "https://run.mocky.io/v3/85cf9aaf-aa4f-41bf-b10c-308f032f7ccc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }

    private fun getData() {
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this)

        // String Request initialized
        mStringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                // display the response on screen
                Toast.makeText(applicationContext, "Response: $response", Toast.LENGTH_LONG).show()
            },
            { error ->
                Log.i("Getdata", "Error: ${error.toString()}")
            })

        // Add the request to the RequestQueue.
        mRequestQueue?.add(mStringRequest)
    }
}
