package com.example.expensemanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R
import com.example.expensemanager.classfolder.IncomeExpenceModelClass

class IncomeExpenceAdapter(var list: ArrayList<IncomeExpenceModelClass>) : RecyclerView.Adapter<IncomeExpenceAdapter.MyViewHolder>() {
    class MyViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
        var txtIncomeDate: TextView = ItemView.findViewById(R.id.txtExpenseDate)
        var txtIncomeAmount: TextView = ItemView.findViewById(R.id.txtIncomeAmount)
        var txtIncomeCategory: TextView = ItemView.findViewById(R.id.txtIncomeCategory)
        var txtIncomeNote: TextView = ItemView.findViewById(R.id.txtIncomeNote)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeExpenceAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.income_item, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: IncomeExpenceAdapter.MyViewHolder, position: Int) {
        holder.txtIncomeDate.text = list[position].Date
        holder.txtIncomeAmount.text = list[position].Amount.toString()
        holder.txtIncomeCategory.text = list[position].Category
        holder.txtIncomeNote.text = list[position].Note

    }
    override fun getItemCount(): Int {
        return list.size
    }
    fun updateData(list: ArrayList<IncomeExpenceModelClass>) {
        this.list = ArrayList()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}