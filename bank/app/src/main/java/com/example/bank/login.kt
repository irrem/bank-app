package com.example.bank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var email:String ?=null
    var password:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance()

        val currentUser= auth.currentUser
        if(currentUser!=null){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    fun loginOnClick(view: View){
        email= emailTxt.text.toString()
        password= findViewById<EditText>(R.id.password).text.toString()
        //val emailText= emailTxt.text.toString()
        if(email!=null && password!=null) {
            auth.signInWithEmailAndPassword(email!!, password!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Giriş Başarılı!", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(
                    applicationContext,
                    exception.localizedMessage.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }else{
            Toast.makeText(applicationContext, "Şifre ve Mail kısımları boş bırakılamaz!", Toast.LENGTH_LONG).show()
        }
    }

}