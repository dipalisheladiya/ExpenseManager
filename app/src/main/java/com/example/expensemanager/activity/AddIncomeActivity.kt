package com.example.expensemanager.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.expensemanager.R
import com.example.expensemanager.adapter.CategoryAdapter
import com.example.expensemanager.adapter.ModeAdapter
import com.example.expensemanager.classfolder.CategoryModelClass
import com.example.expensemanager.classfolder.DatabaseHelper
import com.example.expensemanager.databinding.ActivityAddIncomeBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class AddIncomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddIncomeBinding
    lateinit var databaseHelper: DatabaseHelper
    var categoryList = ArrayList<CategoryModelClass>()
    val mode = arrayOf("Cash", "Cheque", "Online")
    var selectedMode: String = ""
    var selectedCategory: String = ""
    var type: Int = 0
    lateinit var selectedDate: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun initView() {
        binding.txtDate2.setOnClickListener {
            openDatePickerDialog()
        }

        binding.txtTime.setOnClickListener {
            timePicker()
        }

        databaseHelper = DatabaseHelper(this, "ExpenseManager.db", null, 1)
        var rdo = binding.rdoGroup.checkedRadioButtonId
        if (intent.hasExtra("Income")) {
            binding.txtIncomeExpense.setText("Add Income")
            binding.rdoIncome.isChecked = true
            type = 0
        } else if (intent.hasExtra("Expense")) {
            binding.txtIncomeExpense.setText("Add Expense")
            binding.rdoExpense.isChecked = true
            type = 1
        }
        binding.rdoGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            var rdoGroup = radioGroup.checkedRadioButtonId
            var radioButton: RadioButton = binding.rdoGroup.findViewById<RadioButton>(i)

            if (radioButton.text == "Income") {
                type = 0
            } else {
                type = 1
            }
//            Toast.makeText(this, " " + type, Toast.LENGTH_SHORT).show()
        })
        categoryList = databaseHelper.display()
        val categoryAdapter = CategoryAdapter(this, R.layout.category_item, categoryList)
        binding.spinnerCategory.setAdapter(categoryAdapter)

        val modeAdapter = ModeAdapter(this, R.layout.mode_item, mode)
        binding.spinnerMode.setAdapter(modeAdapter)
        if (binding.spinnerCategory != null) {
            val adapter = CategoryAdapter(this, R.layout.category_item, categoryList)
            binding.spinnerCategory.adapter = adapter
            binding.spinnerCategory.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        selectedCategory = categoryList[position].categoryName
                        Log.e("TAG", "onItemSelected: ===>" + selectedCategory)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
        }
        if (binding.spinnerMode != null) {
            val adapter = ModeAdapter(this, R.layout.mode_item, mode)
            binding.spinnerMode.adapter = adapter

            binding.spinnerMode.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        selectedMode = mode[position]
//                    println("Muscle Group selected is $selectedMode")  // <-- this works
                        Log.e("TAG", "onItemSelected: ===>" + selectedMode)

                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
        }
        binding.imgSave.setOnClickListener {

            val Amount: String = binding.edtAmount.text.toString()
            val Date: String = binding.txtDate2.text.toString()
            var Note: String = binding.edtNote.text.toString()

            if (Amount.isEmpty()) {
                Toast.makeText(this, "Please Enter Amount", Toast.LENGTH_SHORT).show()
            } else if (selectedCategory.isEmpty()) {
                Toast.makeText(this, "Please Enter Category", Toast.LENGTH_SHORT).show()
            } else if (Date.isEmpty()) {
                Toast.makeText(this, "Please Enter Date", Toast.LENGTH_SHORT).show()
            } else if (selectedMode.isEmpty()) {
                Toast.makeText(this, "Please Enter Mode", Toast.LENGTH_SHORT).show()
            } else if (Note.isEmpty()) {
                Toast.makeText(this, "Please Enter Note", Toast.LENGTH_SHORT).show()
            } else {
                Log.e(
                    "TAG",
                    "initView: ==>" + Amount + "  " + selectedCategory + "  " + Date + "  " + selectedMode + "  " + Note
                )
                databaseHelper.insertDataIncomeExpense(
                    Amount,
                    selectedCategory,
                    Date,
                    selectedMode,
                    Note,
                    type
                )
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AllTransactionActivity::class.java)
                finish()
                startActivity(intent)
            }

            try {

//                var strDate = "Jan 17, 2012";

                //current date format
                var dateFormat = SimpleDateFormat("MMM dd, yyyy");

                var objDate = dateFormat.parse(Date);

                //Expected date format
                var dateFormat2 = SimpleDateFormat("yyyy-MM-dd");

                var finalDate = dateFormat2.format(objDate);

                Log.e("TAG", "initView:====>" + finalDate)

            } catch (e: Exception) {
                e.printStackTrace();
            }

        }
        binding.imgArrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    fun openDatePickerDialog() {
        var calendar: Calendar = Calendar.getInstance()
        binding.txtDate2.setOnClickListener {
            var datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    var selectedDate: String =
                        dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    binding.txtDate2.text = selectedDate
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.setMaxDate(calendar.timeInMillis)
            datePickerDialog.show()
        }
    }

    fun timePicker() {

        val mTimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                var time : String = String.format("%d : %d", hourOfDay, minute)
                binding.txtTime.text = time
                Log.e("TAG", "onTimeSet: ===>"+time )
            }
        }, hour, minute, false)
        mTimePicker.show()
    }

}