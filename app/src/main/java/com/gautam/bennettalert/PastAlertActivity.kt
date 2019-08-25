package com.gautam.bennettalert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class PastAlertActivity : AppCompatActivity() {
val auth by lazy {
    FirebaseAuth.getInstance()
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_alert)
        val list= listOf("")
        val adapter=AlertAdapter(this,list)
    }
}
