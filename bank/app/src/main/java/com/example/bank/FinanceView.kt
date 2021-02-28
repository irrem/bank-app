package com.example.bank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_finance_view.*
import kotlinx.android.synthetic.main.activity_money_transfer.*

class FinanceView : AppCompatActivity() {

    var db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    val toSendNameListFromDb:ArrayList<String> = ArrayList()
    val toSendNoListFromDb:ArrayList<String> = ArrayList()
    val sendMoneyBalanceListFromDb:ArrayList<String> = ArrayList()
    val dateListFromDb:ArrayList<String> = ArrayList()

    var adapter: FinanceRecyclerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finance_view)

        auth=FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()
        getFinance()
        //recyclerView
        var layoutManager =LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        adapter= FinanceRecyclerAdapter(toSendNameListFromDb,sendMoneyBalanceListFromDb,toSendNoListFromDb,dateListFromDb)
        recyclerView.adapter=adapter
    }

    fun getFinance(){
        db.collection("sendMoney").addSnapshotListener{
            snapshot, exception->
            if(exception!=null)
                Toast.makeText(applicationContext,exception.localizedMessage.toString(), Toast.LENGTH_LONG).show()
            else{
                if(snapshot!=null){
                    if(!snapshot.isEmpty){
                        toSendNameListFromDb.clear()
                        toSendNoListFromDb.clear()
                        sendMoneyBalanceListFromDb.clear()
                        dateListFromDb.clear()


                        val documents=snapshot.documents
                        for(document in documents){
                            val toSendName=document.get("toSendName") as String
                            val toSendNo=document.get("toSendNo") as String
                            val sendMoneyBalance=document.get("sendMoneyBalance") as String
                            val timestamp=document.get("date") as Timestamp
                            val date=timestamp.toDate().toString()

                            toSendNameListFromDb.add(toSendName)
                            toSendNoListFromDb.add(toSendNo)
                            sendMoneyBalanceListFromDb.add(sendMoneyBalance)
                            dateListFromDb.add(date)

                            adapter!!.notifyDataSetChanged()
                        }

                    }

                }
            }
        }
    }
}