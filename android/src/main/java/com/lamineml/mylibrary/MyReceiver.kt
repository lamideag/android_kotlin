package com.lamineml.mylibrary

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            Toast.makeText(context, "SCREEN_ON", Toast.LENGTH_SHORT).show();
        }
        if (Intent.ACTION_PACKAGE_DATA_CLEARED.equals(intent.getAction())) {
            Toast.makeText(context, "DATA  CLEAR", Toast.LENGTH_SHORT).show();
        }
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            Toast.makeText(context, "AIRPLANE_MODE_CHANGED", Toast.LENGTH_SHORT).show();
        }

    }
}