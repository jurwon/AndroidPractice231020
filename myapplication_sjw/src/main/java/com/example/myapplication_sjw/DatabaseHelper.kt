package com.example.myapplication_sjw

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context?) : SQLiteOpenHelper

    (context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table " + TABLE_NAME +

                "(ID TEXT, PASSWORD TEXT, NAME TEXT,GENDER TEXT,PROFILEIMAGE TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)

        onCreate(db)
    }

    fun insertData(id: String?,password: String?, name: String?, gender: String?,profileImage: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)

        contentValues.put(COL_2, password)

        contentValues.put(COL_3, name)

        contentValues.put(COL_4, gender)

        contentValues.put(COL_5, profileImage)

        val result = db.insert(TABLE_NAME, null, contentValues)

        return if (result == -1L) false else true
    }

    val allData: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("select * from $TABLE_NAME", null)
        }

    fun loginCheck(id: String?, password: String?): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("select * from $TABLE_NAME WHERE ID='$id' AND Password='$password'" , null)
    }


    fun updateData(id: String?, password: String?, name: String?,gender: String?,profileImage: String?): Boolean {

        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(COL_1, id)

        contentValues.put(COL_2, password)

        contentValues.put(COL_3, name)

        contentValues.put(COL_4, gender)

        contentValues.put(COL_5, profileImage)

        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))

        return true
    }

    //저장
    companion object {
        const val DATABASE_NAME = "MEMBER.db" // 데이터베이스 명
        const val TABLE_NAME = "member_table" // 테이블 명

        // 테이블 항목
        const val COL_1 = "ID"
        const val COL_2 = "Password"
        const val COL_3 = "Name"
        const val COL_4 = "Gender"
        const val COL_5 = "ProfileImage"

    }

}