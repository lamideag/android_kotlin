package com.lamineml.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lamineml.mylibrary.Lamine

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Lamine().toast("sdvfsdf")
    }
}