package com.lamineml.library

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lamineml.mylibrary.MyReceiver
import java.util.*


class Lamine :ActivityCompat{
    lateinit var ok:Any
    private val context: Context? = null

    private var k = 0

    constructor(activity: Activity)  {
        ok=activity
    }
    constructor(context: Context)  {
        ok=context
    }
    private var tts: TextToSpeech? = null
    fun Dialog(Title: String, Message: String, Icon: Int, Cancelable: Boolean){
        val builder = AlertDialog.Builder(ok as Context)
        builder.setTitle(Title)
        builder.setMessage(Message)
        builder.setIcon(Icon)
        builder.setCancelable(Cancelable)
        builder.show()

    }
    fun wallpaper(resource: Resources, your_image: Int){
        val bitmap: Bitmap = BitmapFactory.decodeResource(resource, your_image)
        val wallpaperManager = WallpaperManager.getInstance(ok as Context)
        wallpaperManager.setBitmap(bitmap)
    }
     fun openApp(packageName: String) {
         var g=ok as        Context
        val launchIntent: Intent? =
            g.getPackageManager()?.getLaunchIntentForPackage(packageName)
        if (launchIntent != null) {
            // null pointer check in case package name was not found
            g.startActivity(launchIntent)

        }

    }
    fun vibrate(Time_vibrate: Long){
        var g=ok as Context
        val v = g.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(
                VibrationEffect.createOneShot(
                    Time_vibrate,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            v.vibrate(Time_vibrate)
        }
    }
    fun share_facebook(your_string: String){
        var g=ok as        Context
        var intent=Intent()
        intent.setAction(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, your_string)
        intent.setPackage("com.facebook.katana")
        g.startActivity(intent)
    }
    fun remove_app(packagename_to_Delete: String){
        var g=ok as        Context
        val intent = Intent(Intent.ACTION_DELETE)
        intent.data = Uri.parse("package:$packagename_to_Delete")
        g.startActivity(intent)
    }
    fun TextToSpeech(your_string: String){
        var g=ok as        Context
        tts = TextToSpeech(g, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {

                tts?.language = Locale.ENGLISH
                tts?.speak(your_string, TextToSpeech.QUEUE_ADD, null)

            } else {
                Toast.makeText(g, "not defined", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun event_system_screen_on(ac: Intent){
        var intentFilter =IntentFilter()
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        var f=ok as Activity
        var context=ok as Context
        f.registerReceiver(MyReceiver(),intentFilter)

    }
    fun hide_icon(){
        var f=ok as Activity
        val p = f.packageManager
    }
    fun event_system_data_clear(){
        var intentFilter =IntentFilter()
        intentFilter.addAction(Intent.ACTION_PACKAGE_DATA_CLEARED)
        var f=ok as Activity
        f.registerReceiver(MyReceiver(),intentFilter)

    }
    fun event_system_airplane(){
        var intentFilter =IntentFilter()
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        var f=ok as Activity
        f.registerReceiver(MyReceiver(),intentFilter)

    }
    fun Call_Phone(phoneNumber: String){
        var g=ok as        Context
        if (ContextCompat.checkSelfPermission(ok as Context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                ok as Activity,
                arrayOf(Manifest.permission.CALL_PHONE),
                24
            )


        } else {

        }

        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))

        g.startActivity(intent)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun ask_system(p: String){
        var g=ok as        Context
        var a =ok as Activity
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$p")
        )
        a.startActivityForResult(intent, 24)
    }
    fun get_language(): String {
        var l=Locale.getDefault().displayLanguage
        return l
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun Setting_System(){
        var g=ok as        Context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(ok as Context)) {
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
            intent.data = Uri.parse(
                "package:" + g.applicationContext.packageName
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            g.startActivity(intent)
        }
    }
}