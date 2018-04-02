package com.resocoder.gsontutorial

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show_orange_liking_users.setOnClickListener {
            val users = getUsers()
            val usersLikingOranges = users.filter {
                it.likesOranges
            }
            textView.text = usersLikingOranges.joinToString()
        }

        btn_save_users_to_preferences.setOnClickListener {
            val usersToSave = listOf(User("Jim", true),
                    User("Susan", true), User("Harry", false),
                    User("Bob", false), User("Kate", true))
            saveUsersToPreferences(usersToSave)
            Toast.makeText(this, "Saved ${usersToSave.size} users to preferences.",
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveUsersToPreferences(users: List<User>) {
        val prefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit()
        val jsonString = Gson().toJson(users)
        prefEditor.putString("users", jsonString).apply()
    }

    private fun getUsers(): List<User> {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val jsonString = preferences.getString("users", null)

        return if (jsonString != null)
            Gson().fromJson(jsonString)
        else
            listOf()
    }
}
