package com.example.bank

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp.now
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_money_transfer.*
import java.sql.Timestamp

class MoneyTransfer : AppCompatActivity() {
    var db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money_transfer)

        auth=FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()

    }

    fun sendMoneyToAccount(view: View) {
       val moneyMap= hashMapOf<String,Any>()
        moneyMap.put("userEmail",auth.currentUser!!.email.toString())
        moneyMap.put("toSendName",toSendName.text.toString())
        moneyMap.put("toSendNo",toSendNo.text.toString())
        moneyMap.put("sendMoneyBalance",sendMoneyBalance.text.toString())
        moneyMap.put("date",com.google.firebase.Timestamp.now())


       db.collection("sendMoney").add(moneyMap).addOnCompleteListener {
           task->
           if(task.isSuccessful){
               Toast.makeText(applicationContext,"Para Gönderme İşlemi Başarılı! Önceki sayfaya yönlendiriliyorsunuz.",Toast.LENGTH_SHORT).show()
               val intent = Intent(applicationContext, MainActivity::class.java)
               startActivity(intent)
               finish()
           }

       }.addOnFailureListener {
           exception->
           Toast.makeText(applicationContext,exception.localizedMessage.toString(),Toast.LENGTH_LONG).show()
       }
    }
}