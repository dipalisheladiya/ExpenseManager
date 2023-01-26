package com.example.expensemanager.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.expensemanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        binding.imgOpenMenu.setOnClickListener{
            binding.drawerLayout.openDrawer(binding.mDrawerLayout)
        }
        binding.loutIncome.setOnClickListener {
            val i = Intent(this, AddIncomeActivity::class.java)
            i.putExtra("Income","Add Income")
            finish()
            startActivity(i)
        }
        binding.loutExpenses.setOnClickListener{
            val i = Intent(this, AddIncomeActivity::class.java)
            i.putExtra("Expense","Add Expense")
            finish()
            startActivity(i)
        }
        binding.loutCategory.setOnClickListener{
            val intent = Intent(this, AddCategoryActivity::class.java)
            startActivity(intent)
        }
        binding.loutTransaction.setOnClickListener{
            val intent = Intent(this, AllTransactionActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Exit App")
        builder.setMessage("Do you want to exit an App?")
        builder.setPositiveButton(
            "Yes"
        ) { dialog, i -> finish() }

        builder.setNegativeButton(
            "No"
        ) { dialog, i -> dialog.cancel() }

        val d = builder.create()
        d.setCancelable(false)
        d.show()
    }
}