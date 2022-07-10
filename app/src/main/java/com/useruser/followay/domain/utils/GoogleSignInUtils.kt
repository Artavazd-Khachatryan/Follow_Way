package com.useruser.followay.domain.utils

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.useruser.followay.domain.models.Account
import com.useruser.followay.domain.models.REQUEST_CODE_GOOGLE_SIGN_IN
private val TAG = "GoogleSignInUtils"

fun Fragment.signInGoogle() {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    val mGoogleSignInClient = GoogleSignIn.getClient(this.context!!, gso)

    val signInIntent = mGoogleSignInClient.signInIntent
    this.startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_SIGN_IN)
}

fun handleGoogleSignInResult(data: Intent?): Account? {

    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
    try {
        val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
        Log.d(TAG, "-----------------idToken-----------------------${account.idToken}")
        Log.d(TAG, "--------------------id--------------------${account.id}")
        Log.d(TAG, "-------------------email---------------------${account.email}")
        Log.d(TAG, "--------------------displayName--------------------${account.displayName}")
        Log.d(TAG, "---------------------photoUrl-------------------${account.photoUrl}")
        Log.d(TAG, "--------------------familyName--------------------${account.familyName}")
        Log.d(TAG, "--------------------grantedScopes--------------------${account.grantedScopes}")
        Log.d(TAG, "-----------------------isExpired-----------------${account.isExpired}")
        Log.d(TAG, "----------------------givenName------------------${account.givenName}")
        Log.d(
            TAG,
            "----------------------serverAuthCode------------------${account.serverAuthCode}"
        )
        Log.d(TAG, "----------------------account------------------${account.account}")

        return Account("", account.email!!)
    } catch (e: ApiException) {
        Log.d(TAG, "=---------------------ApiException---------------- ${e.message}")
        return null

    }
}




