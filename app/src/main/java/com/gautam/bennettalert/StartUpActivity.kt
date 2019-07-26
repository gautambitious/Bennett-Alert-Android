package com.gautam.bennettalert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_main.*
import org.jetbrains.anko.toast

class StartUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
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
        auth = FirebaseAuth.getInstance()

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
}
