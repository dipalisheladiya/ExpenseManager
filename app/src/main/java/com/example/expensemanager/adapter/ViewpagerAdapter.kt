package com.example.expensemanager.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.expensemanager.fragment.ExpenseFragment
import com.example.expensemanager.fragment.IncomeFragment

class ViewpagerAdapter(
    var contaxt: Context,
    var supportFragmentManager: FragmentManager,
    var tabCount: Int
) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return IncomeFragment()
        }
        else if (position==1){
            return  ExpenseFragment()
        }
        else{
            return Fragment()
        }
    }
}