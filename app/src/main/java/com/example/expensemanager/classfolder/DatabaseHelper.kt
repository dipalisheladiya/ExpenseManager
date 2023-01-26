package com.example.expensemanager.classfolder

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.nio.file.Files.move

class DatabaseHelper(
    context: Context,
    category: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, category, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table CategoryTb(Id integer Primary key autoincrement,CategoryName text)"
        db?.execSQL(sql)

        var sql2 =
            "create table IncomeExpenseTb(Id integer Primary key autoincrement,Amount integer,Category text,Date text,Mode text,Note text,Type integer)"
        db?.execSQL(sql2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(CategoryName: String) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put("CategoryName", CategoryName)
        var result = db.insert("CategoryTb", null, contentValues)

        Log.e("TAG", "insertData:==>" + result)
    }

    fun insertDataIncomeExpense(
        Amount: String,
        Category: String,
        Date: String,
        Mode: String,
        Note: String,
        Type: Int
    ) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put("Amount", Amount)
        contentValues.put("Category", Category)
        contentValues.put("Date", Date)
        contentValues.put("Mode", Mode)
        contentValues.put("Note", Note)
        contentValues.put("Type", Type)

        var result = db.insert("IncomeExpenseTb", null, contentValues)

        Log.e("TAG", "insertDataIncomeExpense: ==>" + result)

    }

    fun display(): ArrayList<CategoryModelClass> {
        var categoryList = ArrayList<CategoryModelClass>()
        var db = readableDatabase
        var sql = "select * from CategoryTb"
        var cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()
        do {
            var Id = cursor.getInt(0)
            var CategoryName = cursor.getString(1)
            categoryList.add(CategoryModelClass(Id, CategoryName))
            Log.e("TAG", "display:==> " + Id + "  " + CategoryName)
        } while (cursor.moveToNext())
        return categoryList
    }

    fun displayIncome(): ArrayList<IncomeExpenceModelClass> {
        val list = ArrayList<IncomeExpenceModelClass>()
        val db = readableDatabase
        val sql = "select * from IncomeExpenseTb where Type=0"
        val cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()
        do {
            val Id = cursor.getInt(0)
            val Amount = cursor.getInt(1)
            val Category = cursor.getString(2)
            val Date = cursor.getString(3)
            val Mode = cursor.getString(4)
            val Note = cursor.getString(5)
            val Type = cursor.getInt(6)
            list.add(IncomeExpenceModelClass(Id, Amount, Category, Date, Mode, Note, Type))
            Log.e(
                "TAG",
                "displayIncomeExpense:display ===>" + Id + "  " + Amount + "   " + Category + "   " + Date + "   " + Mode + "   " + Note + "   " + Type
            )
        } while (cursor.moveToNext())
        return list
    }

    fun displayExpense(): ArrayList<IncomeExpenceModelClass> {
        var list = ArrayList<IncomeExpenceModelClass>()
        var db = readableDatabase
        var sql = "select * from IncomeExpenseTb where Type=1"
        var cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()
        do {
            var Id = cursor.getInt(0)
            var Amount = cursor.getInt(1)
            var Category = cursor.getString(2)
            var Date = cursor.getString(3)
            var Mode = cursor.getString(4)
            var Note = cursor.getString(5)
            var Type = cursor.getInt(6)
            list.add(IncomeExpenceModelClass(Id, Amount, Category, Date, Mode, Note, Type))
            Log.e(
                "TAG",
                "displayIncomeExpense:display ===>" + Id + "  " + Amount + "   " + Category + "   " + Date + "   " + Mode + "   " + Note + "   " + Type
            )
        } while (cursor.moveToNext())
        return list
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun dateFilterIncome(selectedDate: String): ArrayList<IncomeExpenceModelClass> {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        var filterDate = dateFormat.parse(selectedDate)
        var list = ArrayList<IncomeExpenceModelClass>()
        var db = readableDatabase
        val sql = "select * from IncomeExpenseTb where Type=0"
        val cursor: Cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                var Id = cursor.getInt(0)
                var Amount = cursor.getInt(1)
                var Category = cursor.getString(2)
                var Date = cursor.getString(3)
                var Mode = cursor.getString(4)
                var Note = cursor.getString(5)
                var Type = cursor.getInt(6)

                var databaseDate = dateFormat.parse(Date)
                if (filterDate.equals(databaseDate))
                {
                    list.add(IncomeExpenceModelClass(Id, Amount, Category, Date, Mode, Note, Type))
                }
                Log.e("TAG", "dateFilterIncome:filter date ==>"+Date )
            } while (cursor.moveToNext())
        }
        return list
    }

//    fun dateFilterExpense(selectedDate: String): ArrayList<IncomeExpenceModelClass> {
//        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
//        var filterDate = dateFormat.parse(selectedDate)
//        var list = ArrayList<IncomeExpenceModelClass>()
//        var db = readableDatabase
//        val sql = "select * from IncomeExpenseTb where Type=0"
//        val cursor: Cursor = db.rawQuery(sql, null)
//        if (cursor.moveToFirst()) {
//            do {
//                var Id = cursor.getInt(0)
//                var Amount = cursor.getInt(1)
//                var Category = cursor.getString(2)
//                var Date = cursor.getString(3)
//                var Mode = cursor.getString(4)
//                var Note = cursor.getString(5)
//                var Type = cursor.getInt(6)
//                list.add(IncomeExpenceModelClass(Id, Amount, Category, Date, Mode, Note, Type))
//            } while (cursor.moveToNext())
//        }
//        return list
//    }
}