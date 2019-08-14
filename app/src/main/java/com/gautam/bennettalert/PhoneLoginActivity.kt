package com.gautam.bennettalert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.isDigitsOnly
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_phone_login.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

class PhoneLoginActivity : AppCompatActivity() {
private val auth: FirebaseAuth by lazy {
    FirebaseAuth.getInstance()
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_login)

        phoneNumberLoginButton.setOnClickListener {
            val number=phoneNumberText.editText?.text
            when {
                number.isNullOrBlank() -> toast("Phone Number Can't be Blank")
                !number!!.isDigitsOnly() -> { toast("Invalid Phone Number")
                }
                else -> {
                    loginUsingPhone("+91$number")
                    otpLoading.show()
                    phoneNumberText.isEnabled=false

                }
            } }
        manualOtpButton.setOnClickListener {
            manualOtpButtonFunction()
        }
    }

    private fun manualOtpButtonFunction() {
        phoneNumberText.isEnabled=false
        
    }

    private fun loginUsingPhone(phoneNumber:String) {

        val callbacks= object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
                longToast("Verification Completed!")
                createAccount(p0!!)
                successLogin()
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                toast("There Was a trouble $p0")
            }

            override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(p0, p1)
                codeSentActions()
            }
        }




        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            callbacks) // OnVerificationStateChangedCallbacks
    }

    private fun successLogin() {

    }

    private fun codeSentActions() {
//        otpLoading.show()
        toast("Waiting To Automatically Verify Otp")
        phoneNumberLoginButton.isEnabled=false
    }

    private fun createAccount(p0: PhoneAuthCredential) {
        auth.signInWithCredential(p0).addOnSuccessListener {
            toast("Successfully SignedIn")
        }
            .addOnFailureListener {
                toast("Couldn't sign in error: $it")
            }
    }
}
