package com.oed.commons.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Looper
import android.widget.Toast
import kotlin.reflect.KClass

fun Context.getColorCompat(id: Int, theme: Resources.Theme? = null) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.resources.getColor(id, theme)
        } else {
            this.resources.getColor(id)
        }

fun Activity.getDrawableCompat(id: Int) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getDrawable(id)!!
        } else {
            this.resources.getDrawable(id)
        }

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    try {

        Toast.makeText(this, message, length).show()
    } catch (e: Exception) {
        //解决在子线程中调用Toast的异常情况处理
        Looper.prepare()
        Toast.makeText(this, message, length).show()
        Looper.loop()
    }
}

fun Activity.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    this.runOnUiThread {
        try {
            Toast.makeText(this, message, length).show()
        } catch (e: Exception) {
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare()
            Toast.makeText(this, message, length).show()
            Looper.loop()
        }
    }
}

fun Context.restartApp() {
    with(this) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}

fun Context.startActivity(cls: Class<*>) {
    val intent = Intent(this, cls)
    this.startActivity(intent)
}

fun Context.startActivity(cls: KClass<*>) {
    val intent = Intent(this, cls.java)
    this.startActivity(intent)
}

fun Context.startActivity(map: Map<String, Any>, cls: Class<*>) {
    val intent = Intent(this, cls)
    intent.putExtra("source", javaClass.simpleName)
    for ((key, value) in map) {
        when (value) {
            is Int -> intent.putExtra(key, value)
            is Long -> intent.putExtra(key, value)
            is Float -> intent.putExtra(key, value)
            is Double -> intent.putExtra(key, value)
            is String -> intent.putExtra(key, value)
            is Boolean -> intent.putExtra(key, value)
        }

    }
    this.startActivity(intent)
}

fun Context.startActivity(map: Map<String, Any>, cls: KClass<*>) {
    val intent = Intent(this, cls.java)
    intent.putExtra("source", javaClass.simpleName)
    for ((key, value) in map) {
        when (value) {
            is Int -> intent.putExtra(key, value)
            is Long -> intent.putExtra(key, value)
            is Float -> intent.putExtra(key, value)
            is Double -> intent.putExtra(key, value)
            is String -> intent.putExtra(key, value)
            is Boolean -> intent.putExtra(key, value)
        }

    }
    this.startActivity(intent)
}

/**
 * @describe 复制到粘贴板
 **/
fun Context.setClipboard(message: String) {
    val cmb = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cmb.primaryClip = ClipData.newPlainText(null, message)
}


