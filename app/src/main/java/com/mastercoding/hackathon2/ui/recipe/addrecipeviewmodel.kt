package com.mastercoding.hackathon2.ui.recipe

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.mastercoding.hackathon2.models.Ingredient
import kotlin.text.StringBuilder


class addrecipeviewmodel(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    var recipeImageUri: Uri? = null
    var ingredientsList = List(5) {""}
    var counter : Int = 0

    companion object {
        private const val DATABASE_NAME = "recipes.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "recipes"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_IMAGE = "image"
        private const val COLUMN_INGREDIENTS = "ingredients"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_IMAGE TEXT, " +
                "$COLUMN_INGREDIENTS TEXT)"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    fun addIngredient(name: String, manufacturer: String){
        val ingredient : String = mapOf(name to manufacturer).toString()
        counter++
    }


    fun addRecipe(name: String, uri: Uri?, ingredients: List<String>){
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_IMAGE, uri.toString())
        values.put(COLUMN_INGREDIENTS, ingredients.toString())

        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun reset() {
        recipeImageUri = null
//        ingredientsList.clear()
    }
}