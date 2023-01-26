package com.example.expensemanager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R
import com.example.expensemanager.adapter.IncomeExpenceAdapter
import com.example.expensemanager.classfolder.DatabaseHelper
import com.example.expensemanager.classfolder.IncomeExpenceModelClass

class IncomeFragment : Fragment() {
    var list = ArrayList<IncomeExpenceModelClass>()
    lateinit var rcvIncome: RecyclerView
    lateinit var databaseHelper: DatabaseHelper
    var incomeExpenceAdapter: IncomeExpenceAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_income, container, false)
        initView(v)
        return v
    }
    private fun initView(v: View) {
        rcvIncome = v.findViewById(R.id.rcvIncome)
        databaseHelper = activity?.let { DatabaseHelper(it, "ExpenseManager.db", null, 1) }!!

        list = databaseHelper.displayIncome()
        incomeExpenceAdapter?.updateData(list)

        incomeExpenceAdapter = IncomeExpenceAdapter(list)
        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rcvIncome.layoutManager = manager
        rcvIncome.adapter = incomeExpenceAdapter

    }
}