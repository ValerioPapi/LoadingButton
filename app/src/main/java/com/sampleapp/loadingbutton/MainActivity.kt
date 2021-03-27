package com.sampleapp.loadingbutton

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.valeriopapi.loadingbutton.LoadingButton

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var normal: Button
    lateinit var loading: Button
    lateinit var success: Button
    lateinit var error: Button
    lateinit var loadingButton: LoadingButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.normal = findViewById(R.id.normal)
        this.loading = findViewById(R.id.loading)
        this.success = findViewById(R.id.success)
        this.error = findViewById(R.id.error)

        this.loadingButton = findViewById(R.id.loadingButton)

        normal.setOnClickListener(this)
        loading.setOnClickListener(this)
        success.setOnClickListener(this)
        error.setOnClickListener(this)
        loadingButton.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.normal -> {
                loadingButton.setIsLoading(false)
            }
            R.id.loading -> {
                loadingButton.setIsLoading(true)
            }
            R.id.success -> {
                loadingButton.setSuccess()
            }
            R.id.error -> {
                loadingButton.setError()
            }

        }

    }
}