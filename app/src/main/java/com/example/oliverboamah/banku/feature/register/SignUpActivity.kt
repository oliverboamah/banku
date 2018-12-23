package com.example.oliverboamah.banku.feature.register

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chootdev.csnackbar.Type
import com.example.oliverboamah.banku.R
import com.example.oliverboamah.banku.feature.route.Route
import com.example.oliverboamah.banku.feature.data.UserData
import com.example.oliverboamah.banku.feature.lib.App
import com.example.oliverboamah.banku.feature.model.Model
import com.fingers.fingers.lib.InputValidation
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var model: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        model = Model(SignUpActivity@ this)
    }

    fun signUp(view: View) {
        when (InputValidation.validateSignUpForm(txtName, txtMobileNumber, txtPin, txtConfirmPin)) {
            true -> {

                if (!model.isRegisteredMobileNumber(txtMobileNumber.text.trim().toString())) {
                    model.addUser(UserData(txtName.text.toString(),
                            txtMobileNumber.text.toString(), txtPin.text.toString(), 0.00))
                    Route.launchLoginActivity(this)
                } else {
                    App.displayAlert(this, Type.ERROR, R.string.error_mobile_number_exists)
                }
            }
        }
    }
}
