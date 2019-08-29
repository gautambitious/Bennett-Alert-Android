package com.gautam.bennettalert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PastAlertActivity : AppCompatActivity() {
val auth by lazy {
    FirebaseAuth.getInstance()
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_alert)
        supportActionBar?.title="Past Alerts"
        val list= listOf("")
        val adapter=AlertAdapter(this,list)
        val c=Calendar.getInstance()
        val fm=SimpleDateFormat("yyyy-MM-dd")
        val a=fm.format(c.time)
        Log.i("workk",a)
        toast(a.toString())
    }
}
