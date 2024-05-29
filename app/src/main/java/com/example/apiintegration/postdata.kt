package com.example.apiintegration

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class PostData : AppCompatActivity() {

    private lateinit var nameEdt: EditText
    private lateinit var jobEdt: EditText
    private lateinit var postDataBtn: Button
    private lateinit var loadingPB: ProgressBar
    private lateinit var responseTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postdata)

        // Initialize variables with their respective views
        nameEdt = findViewById(R.id.idEdtName)
        jobEdt = findViewById(R.id.idEdtJob)
        postDataBtn = findViewById(R.id.idBtnPostData)
        loadingPB = findViewById(R.id.idPBLoading)
        responseTV = findViewById(R.id.idTVResponse)

        // Set click listener for post data button
        postDataBtn.setOnClickListener {
            if (nameEdt.text.toString().isEmpty() && jobEdt.text.toString().isEmpty()) {
                Toast.makeText(
                    this@PostData,
                    "Please enter name and job..",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Call method to post the data
                postDataUsingVolley(nameEdt.text.toString(), jobEdt.text.toString())
            }
        }
    }

    private fun postDataUsingVolley(name: String, job: String) {
        val url = "https://reqres.in/api/users"

        // Show progress bar
        loadingPB.visibility = View.VISIBLE

        val queue = Volley.newRequestQueue(this@PostData)
        val request = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                // Hide progress bar
                loadingPB.visibility = View.GONE

                // Set response to text view
                responseTV.text = "Response from the API is: $response"

                // Show success toast
                Toast.makeText(this@PostData, "Data posted successfully..", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                // Hide progress bar
                loadingPB.visibility = View.GONE

                // Set error message to text view
                responseTV.text = error.message

                // Show error toast
                Toast.makeText(this@PostData, "Failed to get response..", Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["name"] = name
                params["job"] = job
                return params
            }
        }

        // Add request to queue
        queue.add(request)
    }
}
