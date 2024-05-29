package com.example.apiintegration

import android.content.ContentValues
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
import javax.xml.transform.ErrorListener
import javax.xml.transform.TransformerException

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
        mStringRequest = StringRequest(Request.Method.GET, url, object :
            Response.Listener<String?> {
            // display the response on screen
            fun onResponse(response: String) {
                Toast.makeText(applicationContext, "Response :$response", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(response: String?) {
                TODO("Not yet implemented")
            }
        }, object : ErrorListener() {
            fun onErrorResponse(error: VolleyError) {
                Log.i(ContentValues.TAG, "Error :" + error.toString())
            }

            override fun warning(exception: TransformerException?) {
                TODO("Not yet implemented")
            }

            override fun error(exception: TransformerException?) {
                TODO("Not yet implemented")
            }

            override fun fatalError(exception: TransformerException?) {
                TODO("Not yet implemented")
            }
        })
        mRequestQueue!!.add(mStringRequest)
    }
}
