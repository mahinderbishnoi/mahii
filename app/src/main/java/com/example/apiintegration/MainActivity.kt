package com.example.apiintegration

import android.annotation.SuppressLint


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    // on below line we are creating variables.
    lateinit var nameEdt: EditText
    lateinit var jobEdt: EditText
    lateinit var updateBtn: Button
    lateinit var resultTV: TextView
    lateinit var loadingPB: ProgressBar
    var url = "https://reqres.in/api/users/2"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing all our variables.
        nameEdt = findViewById(R.id.idEdtUserName)
        jobEdt = findViewById(R.id.idEdtJob)
        updateBtn = findViewById(R.id.idBtnUpdate)
        resultTV = findViewById(R.id.idTVResponse)
        loadingPB = findViewById(R.id.idPBLoading)

        // on below line we are adding
        // click listener for our update button.
        updateBtn.setOnClickListener {
            // validating user inputs
            if (TextUtils.isEmpty(nameEdt.text) && TextUtils.isEmpty(jobEdt.text)) {
                // on below line we are displaying toast message
                Toast.makeText(this, "Please enter your data..", Toast.LENGTH_SHORT).show()
            }
            addData(nameEdt.text.toString(), jobEdt.text.toString())
        }
    }

    private fun addData(userName: String, job: String) {
        // on below line we are displaying our progress bar.
        loadingPB.visibility = View.VISIBLE

        // creating a new variable for our request queue
        val queue = Volley.newRequestQueue(this@MainActivity)

        // making a string request to update our data and
        // passing method as PUT. to update our data.
        val request: StringRequest =
            object : StringRequest(Request.Method.PUT, url, object : Response.Listener<String?> {
                override fun onResponse(response: String?) {

                    // hiding our progress bar.
                    loadingPB.visibility = View.GONE

                    // inside on response method we are
                    // setting our edit text to empty.
                    jobEdt.setText("")
                    nameEdt.setText("")

                    // on below line we are displaying a toast message as data updated.
                    Toast.makeText(this@MainActivity, "Data Updated..", Toast.LENGTH_SHORT).show()
                    try {
                        // on below line we are extracting data from our json object
                        // and passing our response to our json object.
                        val jsonObject = JSONObject(response)

                        // creating a string for our output.
                        val result =
                            "User Name : " + jsonObject.getString("name") + "\n" + "Job : " + jsonObject.getString(
                                "job"
                            ) + "\n" + "Updated At : " + jsonObject.getString("updatedAt")

                        // on below line we are setting
                        // our string to our text view.
                        resultTV.setText(result)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    // displaying toast message on response failure.
                    Log.e("tag", "error is " + error!!.message)
                    Toast.makeText(this@MainActivity, "Fail to update data..", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
                override fun getParams(): Map<String, String>? {

                    // below line we are creating a map for storing
                    // our values in key and value pair.
                    val params: MutableMap<String, String> = HashMap()

                    // on below line we are passing our key
                    // and value pair to our parameters.
                    params["name"] = userName
                    params["job"] = job

                    // at last we are
                    // returning our params.
                    return params
                }
            }
        // below line is to make
        // a json object request.
        queue.add(request)
    }
}
