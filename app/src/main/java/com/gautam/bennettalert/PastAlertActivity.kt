package com.gautam.bennettalert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_past_alert.*
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
        val list= listOf("","","","","","","","","","")
//        pastAlerts.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        pastAlerts.layoutManager=GridLayoutManager(this,5,LinearLayoutManager.VERTICAL,false)
        pastAlerts.adapter=AlertAdapter(this,list)
//        val c=Calendar.getInstance()
//        val fm=SimpleDateFormat("yyyy-MM-dd")
//        val a=fm.format(c.time)
//        Log.i("workk",a)
//        toast(a.toString())
    }
}
