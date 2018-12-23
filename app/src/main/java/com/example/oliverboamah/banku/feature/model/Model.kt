package com.example.oliverboamah.banku.feature.model

import android.content.Context
import com.example.oliverboamah.banku.feature.data.UserData
import com.example.oliverboamah.banku.feature.util.PreferencesUtil

/**
 * Created by Oliver Boamah on 11/29/2018.
 */
class Model(private var context: Context) {

    var usersData = PreferencesUtil.getUsersData(context)

    fun getUserIndex(mobileNumber: String, pin: String) : Int
    {
        usersData
                .filter { it.mobileNumber == mobileNumber && it.pin == pin }
                .forEach { return usersData.indexOf(it) }

        return -1
    }

    fun getAccountBalance(userIndex: Int): Double
    {
        return usersData[userIndex].accountBalance
    }

    fun deposit(amount: Double, userIndex: Int)
    {
        usersData[userIndex].accountBalance += amount
        PreferencesUtil.saveUsersData(context, usersData)
    }

    fun withdraw(amount: Double, userIndex: Int)
    {
        usersData[userIndex].accountBalance -= amount
        PreferencesUtil.saveUsersData(context, usersData)
    }

    fun sendMoney(amount: Double, userIndex: Int, recipientMobileNumber: String): Boolean
    {
        var counter = 0

        usersData.forEach { user ->
            if(user.mobileNumber == recipientMobileNumber)
            {
                this.deposit(amount, counter) // credit the recipient
                this.withdraw(amount, userIndex) // debit the sender

                return true
            }
            counter ++
        }

        return false
    }

    fun addUser(userData: UserData)
    {
        usersData.add(userData)
        PreferencesUtil.saveUsersData(context, usersData)
    }

    fun isRegisteredMobileNumber(mobileNumber: String) : Boolean
    {
        usersData
                .filter { it.mobileNumber == mobileNumber}
                .forEach { return true}

        return false
    }
}