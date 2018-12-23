package com.fingers.fingers.lib

import android.widget.EditText
import java.util.regex.Pattern

/**
 * Created by codingoliver on 8/16/2018.
 */
class InputValidation {

    companion object {
        fun validateSignUpForm(txtName: EditText, txtMobileNumber: EditText, txtPin: EditText, txtConfirmPin: EditText): Boolean {

            if (this.emptyField(txtName, "Enter name")) {
                return false
            } else if (!this.atLeast6Chars(txtName)) {
                return false
            } else if (this.emptyField(txtMobileNumber, "Enter Mobile number")) {
                return false
            } else if (!this.atLeast10Chars(txtMobileNumber)) {
                return false
            } else if (this.emptyField(txtPin, "Enter PIN")) {
                return false
            } else if (!this.atLeast6Chars(txtPin)) {
                return false
            } else if (this.emptyField(txtConfirmPin, "Enter Confirm PIN")) {
                return false
            } else if (!this.atLeast6Chars(txtConfirmPin)) {
                return false
            } else if ((txtPin.text.trim() != txtConfirmPin.text.trim()) || (txtPin.text.length != txtConfirmPin.text.length)) {
                txtConfirmPin.error = "PIN mismatch"
                txtConfirmPin.requestFocus()
                return false
            }
            return true
        }

        fun validateLoginForm(txtMobileNumber: EditText, txtPin: EditText): Boolean {
            if (this.emptyField(txtMobileNumber, "Enter Mobile number")) {
                return false
            } else if (this.emptyField(txtPin, "Enter PIN")) {
                return false
            } else if (!this.atLeast6Chars(txtPin)) {
                return false
            }
            return true
        }

        private fun emptyField(txtView: EditText, errorMsg: String): Boolean {
            if (txtView.text.isEmpty()) {
                txtView.error = errorMsg
                txtView.requestFocus()
                return true
            }
            return false
        }

        private fun atLeast6Chars(txtView: EditText): Boolean {
            if (txtView.text.trim().length < 6) {
                txtView.error = "Minimum chars = 6"
                txtView.requestFocus()
                return false
            }
            return true
        }

         fun atLeast10Chars(txtView: EditText): Boolean {
            if (txtView.text.trim().length < 10) {
                txtView.error = "Minimum chars = 10"
                txtView.requestFocus()
                return false
            }
            return true
        }

        fun validateDepositForm(txtAmount: EditText): Boolean {
            if (this.emptyField(txtAmount, "Enter Amount")) {
                return false
            }
            return true
        }

        fun validateWithdrawalForm(txtAmount: EditText, txtPin: EditText): Boolean {
            if (this.emptyField(txtAmount, "Enter Amount")) {
                return false
            }else if (this.emptyField(txtPin, "Enter PIN")) {
                return false
            }
            return true
        }

        fun validateSendMoneyForm(txtMobileNumber: EditText, txtAmount: EditText, txtPin: EditText): Boolean {
            return when {
                this.emptyField(txtMobileNumber, "Enter Mobile number") -> false
                this.emptyField(txtAmount, "Enter amount") -> false
                this.emptyField(txtPin, "Enter PIN") -> false
                else -> true
            }
        }

    }
}