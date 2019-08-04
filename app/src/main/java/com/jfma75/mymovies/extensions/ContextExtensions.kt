package com.jfma75.mymovies.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.jfma75.mymovies.application.MyMoviesApp

val Context.app: MyMoviesApp
    get() = applicationContext as MyMoviesApp

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.isConnected(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val connection = manager.activeNetworkInfo
    return connection?.isConnected ?: false
}

fun Context.alert(message: String, title: String) {
    AlertDialog.Builder(this)
        .setTitle(title).setMessage(message)
        .setPositiveButton(android.R.string.ok, null).show()
}

inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit) =
    Intent(this, T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}