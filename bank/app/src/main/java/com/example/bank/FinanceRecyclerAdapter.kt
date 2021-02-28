package com.example.bank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView

class FinanceRecyclerAdapter(
    private val sendNameArray:ArrayList<String>,
    private val sendMoneyBalanceArray:ArrayList<String>,
    private val toSendNoArray:ArrayList<String>,
    private val dateArray:ArrayList<String>):RecyclerView.Adapter<FinanceRecyclerAdapter.FinanceHolder>()
{
    class FinanceHolder(view: View) :RecyclerView.ViewHolder(view)
    {
        var RecycleDateTxt:TextView?=null
        var RecycleToSendNoTxt:TextView?=null
        var RecycleToSendNameTxt:TextView?=null
        var RecycleSendMoneyBalanceTxt:TextView?=null

        init{
            RecycleDateTxt= view.findViewById(R.id.dateTxt)
            RecycleToSendNoTxt=view.findViewById(R.id.toSendNoTxt)
            RecycleToSendNameTxt=view.findViewById(R.id.toSendNameTxt)
            RecycleSendMoneyBalanceTxt=view.findViewById(R.id.sendMoneyBalanceTxt)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanceHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_view_row,parent,false)
        return FinanceHolder(view)
    }

    override fun onBindViewHolder(holder: FinanceHolder, position: Int) {
        holder.RecycleDateTxt?.text=dateArray[position]
        holder.RecycleToSendNoTxt?.text=toSendNoArray[position]
        holder.RecycleSendMoneyBalanceTxt?.text=sendMoneyBalanceArray[position]
        holder.RecycleToSendNameTxt?.text=sendNameArray[position]
    }

    override fun getItemCount(): Int {
        return dateArray.size
    }

}