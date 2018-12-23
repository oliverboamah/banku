package com.example.oliverboamah.banku.feature.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.oliverboamah.banku.R
import android.support.v4.view.ViewCompat
import android.support.v7.widget.AppCompatButton
import android.os.Build
import android.content.res.ColorStateList
import android.content.res.ColorStateList.valueOf
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.chootdev.csnackbar.Type
import com.example.oliverboamah.banku.feature.data.UserData
import com.example.oliverboamah.banku.feature.lib.App
import com.example.oliverboamah.banku.feature.model.Model
import com.example.oliverboamah.banku.feature.route.Route
import com.fingers.fingers.lib.InputValidation
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.card_account_balance.*
import kotlinx.android.synthetic.main.item_deposit.view.*
import kotlinx.android.synthetic.main.item_send_money.view.*
import kotlinx.android.synthetic.main.item_withdraw.view.*


class HomeActivity : AppCompatActivity() {

    private lateinit var model: Model
    var userIndex = -1
    private lateinit var user: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        this.setAppBar()
        this.retrieveUser()
        this.setUpAccountBalanceView()
    }

    private fun setUpAccountBalanceView() {

        if (userIndex > -1) {
            lblAccountBalance.text = model.getAccountBalance(userIndex).toString()
            println("happy")
        }
    }

    private fun retrieveUser() {
        model = Model(SignUpActivity@ this)
        userIndex = intent.getIntExtra("userIndex", -1)
        user = model.usersData[userIndex]
    }

    private fun setAppBar() {
        setSupportActionBar(toolbar as Toolbar?)
    }


    private fun setButtonTint(button: FloatingActionButton, tint: ColorStateList) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP && button is AppCompatButton) {
            button.supportBackgroundTintList = tint
        } else {
            ViewCompat.setBackgroundTintList(button, tint)
        }
    }

    private fun setUpFabs() {
        setButtonTint(fabSend, valueOf(resources.getColor(R.color.colorLighBlue)))
        setButtonTint(fabWithdraw, valueOf(resources.getColor(R.color.colorAccent)))
        setButtonTint(fabDeposit, valueOf(resources.getColor(R.color.colorPrimaryDark)))
    }

    public fun deposit(view: View) {

        val dialogBuilder = AlertDialog.Builder(this)

        val dialogView = this.layoutInflater.inflate(R.layout.item_deposit, null)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        // handle click on deposit button
        dialogView.btnDeposit.setOnClickListener {

            if (InputValidation.validateDepositForm(dialogView.txtAmount)) {
                model.deposit(dialogView.txtAmount.text.trim().toString().toDouble(), userIndex)
                alertDialog.cancel()
                App.displayAlert(this, Type.SUCCESS, R.string.success_deposit)
                this.setUpAccountBalanceView()
            }
        }

        alertDialog.show()
    }

    public fun withdraw(view: View) {

        val dialogBuilder = AlertDialog.Builder(this)

        val dialogView = this.layoutInflater.inflate(R.layout.item_withdraw, null)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        // handle click on deposit button
        dialogView.btnWithdraw.setOnClickListener {

            if (InputValidation.validateWithdrawalForm(dialogView.txtWithdrawalAmount, dialogView.txtUserPin)) {
                if (dialogView.txtUserPin.text.trim().toString() == user.pin) {
                    if (user.accountBalance >= dialogView.txtWithdrawalAmount.text.trim().toString().toDouble()) {
                        model.withdraw(dialogView.txtWithdrawalAmount.text.trim().toString().toDouble(), userIndex)
                        alertDialog.cancel()
                        App.displayAlert(this, Type.SUCCESS, R.string.success_withdraw)
                        this.setUpAccountBalanceView()
                    } else {
                        App.displayAlert(this, Type.ERROR, R.string.not_enough_funds)
                    }

                } else {
                    App.displayAlert(this, Type.ERROR, R.string.wrong_pin)
                }
            }
        }

        alertDialog.show()
    }

    fun sendMoney(view: View) {

        val dialogBuilder = AlertDialog.Builder(this)

        val dialogView = this.layoutInflater.inflate(R.layout.item_send_money, null)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()

        // handle click on deposit button
        dialogView.btnSend.setOnClickListener {

            if (InputValidation.validateSendMoneyForm(dialogView.txtMobileNumber, dialogView.txtSendMoneyAmount, dialogView.txtSendMoneyPin)) {
                if (dialogView.txtSendMoneyPin.text.trim().toString() == user.pin) {
                    if (user.accountBalance >= dialogView.txtSendMoneyAmount.text.trim().toString().toDouble()) {
                        if (model.sendMoney(dialogView.txtSendMoneyAmount.text.trim().toString().toDouble(),
                                userIndex, dialogView.txtMobileNumber.text.trim().toString())) {
                            alertDialog.cancel()
                            App.displayAlert(this, Type.SUCCESS, R.string.success_send_money)
                            this.setUpAccountBalanceView()
                        } else {
                            App.displayAlert(this, Type.ERROR, R.string.not_exist_recipient)
                        }

                    } else {
                        App.displayAlert(this, Type.ERROR, R.string.not_enough_funds)
                    }

                } else {
                    App.displayAlert(this, Type.ERROR, R.string.wrong_pin)
                }

            }
        }

        alertDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sign_out -> Route.launchLoginActivity(this)
        }
        return true
    }
}