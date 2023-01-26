package com.example.expensemanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.expensemanager.R
import com.example.expensemanager.activity.AddIncomeActivity

class ModeAdapter(var addIncomeActivity: AddIncomeActivity, mode1: Int, var mode: Array<String>) : BaseAdapter() {
    private var inflater: LayoutInflater = LayoutInflater.from(addIncomeActivity)
    override fun getCount(): Int {
       return mode.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
       return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = inflater.inflate(R.layout.mode_item, parent, false)
        var txtModeList: TextView = view.findViewById(R.id.txtModeList)
        txtModeList.setText(mode[position])
        return view
    }
}