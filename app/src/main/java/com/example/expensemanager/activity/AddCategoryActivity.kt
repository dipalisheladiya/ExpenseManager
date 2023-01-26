package com.example.expensemanager.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.expensemanager.classfolder.CategoryModelClass
import com.example.expensemanager.classfolder.DatabaseHelper
import com.example.expensemanager.databinding.ActivityAddCategoryBinding

class AddCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddCategoryBinding
    lateinit var databaseHelper : DatabaseHelper
    var categoryList = ArrayList<CategoryModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
         databaseHelper = DatabaseHelper(this,"ExpenseManager.db",null,1)
        binding.btnAdd.setOnClickListener{
            var category : String = binding.edtAddCategory.text.toString()

            if(category.isEmpty())
            {
                Toast.makeText(this, "Please Enter category", Toast.LENGTH_SHORT).show()
            }
            else{
                databaseHelper.insertData(category)

                val i = Intent(this, AddIncomeActivity::class.java)
                finish()
                startActivity(i)

            }
        }
    }
}