package com.gautam.bennettalert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.login_main.*
import org.jetbrains.anko.*
import android.media.RingtoneManager



class StartUpActivity : AppCompatActivity() {
    val auth by lazy{
        FirebaseAuth.getInstance()
    }
    val cloudDb by lazy {
        FirebaseFirestore.getInstance()
    }

    // [END declare_auth]
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_up)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        loginButton.setOnClickListener {
            startActivity<MainActivity>()
        }
        googleSignInButton.setOnClickListener{
            signInWithGoogle()
        }

    }

    private fun signInWithGoogle() {
        val signInIntent= googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== RC_SIGN_IN){
            val task=GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>?) {
        try{
            val account=completedTask?.getResult(ApiException::class.java)
            updateUI(account)
        }
        catch (e: ApiException){
            toast(e.toString())
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        toast("You're logged in"+account?.displayName)
    }

    companion object{
        const val RC_SIGN_IN=1234
    }

    fun playRing(){
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(applicationContext, notification)
        r.play()
    }
}
