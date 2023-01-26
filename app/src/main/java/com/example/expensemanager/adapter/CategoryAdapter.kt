package com.example.expensemanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.expensemanager.R
import com.example.expensemanager.activity.AddIncomeActivity
import com.example.expensemanager.activity.MainActivity
import com.example.expensemanager.classfolder.CategoryModelClass
import java.util.ArrayList

class CategoryAdapter(
    var context: Context,
    category1: Int,
    var category: ArrayList<CategoryModelClass>
) :
    BaseAdapter() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return category.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = inflater.inflate(R.layout.category_item, parent, false)
        var txtList: TextView = view.findViewById(R.id.txtList)
//        txtList.setText(category[position])
        txtList.text=(category[position].categoryName)
        return view
    }
}