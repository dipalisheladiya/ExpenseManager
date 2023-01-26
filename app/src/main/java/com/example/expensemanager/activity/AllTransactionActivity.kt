package com.example.expensemanager.activity

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.expensemanager.adapter.IncomeExpenceAdapter
import com.example.expensemanager.adapter.ViewpagerAdapter
import com.example.expensemanager.classfolder.DatabaseHelper
import com.example.expensemanager.classfolder.IncomeExpenceModelClass
import com.example.expensemanager.databinding.ActivityAllTransactionBinding
import com.google.android.material.tabs.TabLayout
import java.util.*

class AllTransactionActivity : AppCompatActivity() {
    lateinit var binding: ActivityAllTransactionBinding
    var list = ArrayList<IncomeExpenceModelClass>()
    var databaseHelper: DatabaseHelper? = null
    var incomeExpenceAdapter: IncomeExpenceAdapter? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun initView() {
        binding.lout.setOnClickListener {
            onBackPressed()
        }
//        binding.imgCalender.setOnClickListener {
//            openDatePickerDialog()
//        }
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Income"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Expense"))
        binding.tabLayout.setTabTextColors(Color.parseColor("#19678B"),Color.parseColor(("#000000")))
        var viewpagerAdapter =
            ViewpagerAdapter(this, supportFragmentManager, binding.tabLayout.tabCount)
        binding.viewPager.adapter = viewpagerAdapter
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager?.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun openDatePickerDialog() {
        var calendar: Calendar = Calendar.getInstance()
//        binding.imgCalender.setOnClickListener {
            var datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                   var selectedDate : String= dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
//                    binding.txtDate2.setText(selectedDate)

                    Log.e("TAG", "openDatePickerDialog: ===>"+selectedDate )
                    list.clear()
                    list= databaseHelper?.dateFilterIncome(selectedDate)!!
                    incomeExpenceAdapter?.updateData(list)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.setMaxDate(calendar.timeInMillis)
            datePickerDialog.show()
        }
    }
