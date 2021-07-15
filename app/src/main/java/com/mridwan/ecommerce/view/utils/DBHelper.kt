package com.mridwan.ecommerce.view.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mridwan.ecommerce.model.ProductHistory
import com.mridwan.ecommerce.model.response.Product
import java.lang.Exception

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_HISTORY_PRODUCT_TABLE = (
                "CREATE TABLE "+ TABLE_NAME+"("+
                        COLUMN_ID+" INTEGER PRIMARY KEY,"+
                        COLUMN_TITLE+" TEXT,"+
                        COLUMN_DESCRIPTION+" TEXT,"+
                        COLUMN_IMAGELINK+" TEXT,"+
                        COLUMN_PRICE+" TEXT,"+
                        COLUMN_LOVED+" INTEGER)"
                )
        p0!!.execSQL(CREATE_HISTORY_PRODUCT_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(p0)
    }

    fun addProduct(history: ProductHistory) {
        val values = ContentValues()
        values.put(COLUMN_TITLE, history.title)
        values.put(COLUMN_DESCRIPTION, history.description)
        values.put(COLUMN_PRICE, history.price)
        values.put(COLUMN_LOVED, history.loved)
        values.put(COLUMN_IMAGELINK, history.imageLink)
        val db = this.writableDatabase
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun getAllProduct(): ArrayList<Product> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val product:ArrayList<Product> = ArrayList<Product>()
        while (cursor.moveToNext()) {
            try {
                val item = Product(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_IMAGELINK)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_LOVED))
                )
                product.add(item)
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
        cursor.close()
        return product
    }

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "ecommerce.db"
        val TABLE_NAME = "history_product"
        val COLUMN_ID = "id"
        val COLUMN_TITLE = "title"
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_PRICE = "price"
        val COLUMN_IMAGELINK = "image_link"
        val COLUMN_LOVED = "loved"
    }

}