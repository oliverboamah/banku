package com.example.oliverboamah.banku.feature.route

import android.content.Context
import android.content.Intent
import com.example.oliverboamah.banku.feature.home.HomeActivity
import com.example.oliverboamah.banku.feature.login.LoginActivity
import com.example.oliverboamah.banku.feature.register.SignUpActivity

/**
 * Created by Oliver Boamah on 11/29/2018.
 */
class Route {

    companion object {

        fun launchHomeActivity(context: Context,  userIndex: Int)
        {
            var intent = Intent(context, HomeActivity::class.java)
            intent.putExtra("userIndex", userIndex)
            context.startActivity(intent)
        }
        fun launchSignUpActivity(context: Context)
        {
            var intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }

        fun launchLoginActivity(context: Context) {
            var intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }

    }
}