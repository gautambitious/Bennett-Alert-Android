package com.gautam.bennettalert

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.gautam.bennettalert.Alert
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_new_alert.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class NewAlertActivity : AppCompatActivity() {
    // local db instance
    val localDb by lazy {
        AlertDatabase.createDatabase(this)
    }
    val auth by lazy{
        FirebaseAuth.getInstance()
    }
//    val queryId=localDb.alertDao().getMaxId()+1
    // firebase db instance
    val cloudDb=FirebaseDatabase.getInstance().reference



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
//        saveNewAlertToLocal()
//        val ringtone=defaultRingtone
//        ringtone.play()
        cloudUpdate()
    }
    private val Context.defaultRingtone: Ringtone
        get() {
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            return RingtoneManager.getRingtone(this, uri)
        }
    private fun saveNewAlertToLocal() {
//        val a= Alert(localDb.alertDao().getMaxId(),"ad","ads","",true)
//        localDb.alertDao().insertRow(a)
    }

    private fun cloudUpdate() {
     val ref=cloudDb.child("people")
        ref.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {
                longToast("Didn't work cause $p0")
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                toast("${p0.value}")
                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot) {
               //To change body of created functions use File | Settings | File Templates.
            }

        })
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
