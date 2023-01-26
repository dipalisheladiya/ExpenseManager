package com.example.expensemanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R
import com.example.expensemanager.classfolder.IncomeExpenceModelClass

class ExpenseAdapter(var list: ArrayList<IncomeExpenceModelClass>): RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>() {


    class MyViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
        var txtExpenseDate: TextView = ItemView.findViewById(R.id.txtExpenseDate)
        var txtExpenseAmount: TextView = ItemView.findViewById(R.id.txtExpenseAmount)
        var txtExpenseCategory: TextView = ItemView.findViewById(R.id.txtExpenseCategory)
        var txtExpenseNote: TextView = ItemView.findViewById(R.id.txtExpenseNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtExpenseDate.text = list[position].Date
        holder.txtExpenseAmount.text = list[position].Amount.toString()
        holder.txtExpenseCategory.text = list[position].Category
        holder.txtExpenseNote.text = list[position].Note
    }

    override fun getItemCount(): Int {
        return list.size    }

    fun updateData(list: ArrayList<IncomeExpenceModelClass>) {
        this.list = ArrayList()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}