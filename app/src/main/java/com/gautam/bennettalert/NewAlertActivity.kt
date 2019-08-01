package com.gautam.bennettalert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_new_alert.*
import org.jetbrains.anko.toast

class NewAlertActivity : AppCompatActivity() {
    // local db instance
    val localDb by lazy {
        AlertDatabase.createDatabase(this)
    }

    // firebase db instance
    val cloudDb=FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_alert)
        // Drop Down Menu Adapter
        ArrayAdapter.createFromResource(
            this,
            R.array.priority_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            prioritySpinner.adapter = adapter
        }
        cloudDb.collection("Alerts")
            .add(Alert(123,"","sdsd",true))
            .addOnSuccessListener {
                toast("Bro nigga works")
            }
            .addOnFailureListener {
                toast("Bro nigga is bitch")
            }

        cloudUpdate()
    }

     private fun cloudUpdate() {
        val docRef=cloudDb.collection("Alerts")
//            docRef.addSnapshotListener { snapshot, e ->
//            if (e != null) {
//                Log.w(TAG, "Listen failed.", e)
//                return@addSnapshotListener
//            }
//
//            if (snapshot != null && snapshot.exists()) {
//                Log.d(TAG, "Current data: ${snapshot.data}")
//            } else {
//                Log.d(TAG, "Current data: null")
//            }
//        }
    }
    companion object{
        const val TAG="workk"
    }
}
