package com.example.myapp_test6

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper

    (context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table " + TABLE_NAME +

                "(ID TEXT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT,PROFILEIMAGE TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)

        onCreate(db)
    }

    //저장

    // 자바(static) , 클래스 변수 -> 클래스명.  접근.

    // 데이터베이스 추가하기 insert

    fun insertData(id: String?, name: String?, email: String?,password: String?,profileImage: String?): Boolean {

        // 디비 사용시 쓰기, 수정, 삭제 ->writableDatabase 사용함.

        val db = this.writableDatabase

        // execSQL -> 대신에 ContentValues() 를 이용하면

        // SQL  문장 없이, 바로 메서드에 값만 인자로 넣어서

        // 이용하면, 쉽게 insert를 구현가능.

        val contentValues = ContentValues()
        contentValues.put(COL_1, id)

        contentValues.put(COL_2, name)

        contentValues.put(COL_3, email)

        contentValues.put(COL_4, password)

        contentValues.put(COL_5, profileImage)

        val result = db.insert(TABLE_NAME, null, contentValues)

        return if (result == -1L) false else true

    }

    //데이터베이스 항목 읽어오기 Read
    //Cursor = table

    val allData: Cursor

        get() {

            val db = this.writableDatabase

            return db.rawQuery("select * from $TABLE_NAME", null)

        }


    //데이터베이스 수정하기

    fun updateData(id: String?, name: String?, email: String?,password: String?,profileImage: String?): Boolean {

        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(COL_1, id)

        contentValues.put(COL_2, name)

        contentValues.put(COL_3, email)

        contentValues.put(COL_4, password)

        contentValues.put(COL_5, profileImage)

        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))

        return true

    }

    //저장

    // 자바(static) , 클래스 변수 -> 클래스명.  접근.

    companion object {

        // DatabaseHelper 클래스명 -> mydb

        // mydb.DATABASE_NAME -> 사용가능.

        const val DATABASE_NAME = "MEMBER.db" // 데이터베이스 명

        const val TABLE_NAME = "member_table" // 테이블 명




        // 테이블 항목

        const val COL_1 = "ID"

        const val COL_2 = "Name"

        const val COL_3 = "Email"

        const val COL_4 = "Password"

        const val COL_5 = "profileImage"

    }


}