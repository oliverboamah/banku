package com.example.oliverboamah.banku.feature.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import com.example.oliverboamah.banku.R
import com.example.oliverboamah.banku.feature.route.Route
import com.example.oliverboamah.banku.feature.util.PreferencesUtil

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        this.toNextScreenAfterAWhile()
    }

    private fun toNextScreenAfterAWhile() {

        var handler = Handler()
        handler.postDelayed({

            when ( PreferencesUtil.isFirstTime(SplashActivity@this)) {
                true -> Route.launchSignUpActivity(SplashActivity@ this)
                else -> Route.launchLoginActivity(SplashActivity@this)
            }

        }, 5000)
    }
}
