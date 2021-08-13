package com.example.emoji_status

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_login_acitivity.*


class Login_Acitivity : AppCompatActivity() {

    lateinit var  mp: MediaPlayer

    private companion object{
        private const val TAG= "Login_Activity"
        private const val RC_GOOGLE_SIGN_IN=4926
    }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_acitivity)




        playtransition()
        notedeck.alpha=0f
        imageView.alpha=0f
        btnsignin.isClickable=false
        btnsignin.alpha=0f
        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {
            setProgressWithAnimation(100f, 6000) // =5s
            progressMax = 100f
            roundBorder = true
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT}

        circularProgressBar.onProgressChangeListener = { progress ->
                percent.text = "${progress.toInt()}%"

                if (progress == 100f) {
                    circularProgressBar.alpha = 0f
                    percent.alpha=0f
                    btnsignin.alpha=1f
                    btnsignin.isClickable=true
                    notedeck.alpha=1f
                    imageView.alpha=1f
                    loading.alpha=0f

                }

            }

        auth=Firebase.auth

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val client : GoogleSignInClient=GoogleSignIn.getClient(this,gso)

        btnsignin.setOnClickListener {
            val signinintent=client.signInIntent
            startActivityForResult(signinintent,RC_GOOGLE_SIGN_IN)
        }

    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        //navigate to the mainactivity
        if(user==null)
        {
            Log.w(TAG,"User is null, not goig to navigate")
            return
        }
        startActivity(Intent(this,bufferactivitu::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }

    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(this,"Authentication Failed",Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
        }
    }
    private fun playtransition(){
        mp= MediaPlayer.create(this,R.raw.transition)
        mp.start()
    }
    private fun playadd(){
        mp= MediaPlayer.create(this,R.raw.add)
        mp.start()
    }
    private fun playremove(){
        mp= MediaPlayer.create(this,R.raw.delete)
        mp.start()
    }

}