package com.example.expensemanager.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R
import com.example.expensemanager.classfolder.DatabaseHelper
import com.example.expensemanager.adapter.ExpenseAdapter
import com.example.expensemanager.classfolder.IncomeExpenceModelClass

class ExpenseFragment : Fragment() {
    var list = ArrayList<IncomeExpenceModelClass>()
    lateinit var rcvIncome: RecyclerView
    lateinit var databaseHelper: DatabaseHelper
    var expenseAdapter: ExpenseAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_expense, container, false)
        initView(v)
        return v
    }

    private fun initView(v: View) {
        rcvIncome = v.findViewById(R.id.rcvIncome)
        databaseHelper = activity?.let { DatabaseHelper(it, "ExpenseManager.db", null, 1) }!!

        list = databaseHelper.displayExpense()
        expenseAdapter?.updateData(list)


        expenseAdapter = ExpenseAdapter(list)
        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rcvIncome.layoutManager = manager
        rcvIncome.adapter = expenseAdapter

    }

}