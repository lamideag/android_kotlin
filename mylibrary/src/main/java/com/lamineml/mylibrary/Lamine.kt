package com.lamineml.mylibrary

import android.content.Context
import android.widget.Toast

class Lamine {
    lateinit var context: Context
    fun toast(text:String){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show()
    }
}