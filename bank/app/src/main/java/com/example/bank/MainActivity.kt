package com.example.bank

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    private val TAG = "MyActivity"
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater= menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
        db.collection("accounts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG,"${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logout){
            auth.signOut()
            val intent= Intent(applicationContext, login::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //id "kotlin-android-extensions"
        auth= FirebaseAuth.getInstance()
    }
    fun viewMoneyTransferFunc(view: View) {}
    fun sendMoneyFunc(view: View) {
        val intent= Intent(applicationContext, MoneyTransfer::class.java)
        startActivity(intent)
        finish()
    }
    fun moneyMood(view: View) {}
}