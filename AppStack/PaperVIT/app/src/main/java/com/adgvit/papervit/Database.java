package com.adgvit.papervit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "FAV_EXAMS";
    private static final String Col_1 = "Exam_Type";
    private static final String Col_2 = "Subject";

    public Database(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                Col_1 + " TEXT, " +
                Col_2 + " TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addFav(String examType, String courseCode)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1,examType);
        contentValues.put(Col_2,courseCode);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public void deleteFav(String examType, String courseCode)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + Col_1 + " = \"" + examType + "\" and " + Col_2 + " = \"" + courseCode + "\"" ;
        sqLiteDatabase.execSQL(query);
    }

    public Cursor getFav(String examType)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + Col_2 + " FROM " + TABLE_NAME + " WHERE " + Col_1 + " = \"" + examType + "\"";
        Cursor data = sqLiteDatabase.rawQuery(query,null);
        return data;
    }
}
