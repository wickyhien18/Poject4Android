package com.example.thuchanh4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DbName = "Examinee";
    public static final int Db_Version = 1;
    public static final String TABLE_NAME = "EXAMINEE";
    public static final String COL_ID = "Id";
    public static final String COL_FULLNAME = "FullName";
    public static final String COL_IMAGE = "Image";
    public static final String COL_MATH = "Math";
    public static final String COL_PHYSIC = "Physic";
    public static final String COL_CHEMICAL = "Chemical";

    public DbHelper(Context context) {
        super(context,DbName,null,Db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_FULLNAME + " TEXT NOT NULL, "
                + COL_IMAGE + " TEXT, "
                + COL_MATH + " REAL, "
                + COL_PHYSIC + " REAL, "
                + COL_CHEMICAL + " REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(Examinee examinee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COL_FULLNAME, examinee.getFullName());
        value.put(COL_IMAGE, examinee.getImage());
        value.put(COL_MATH, examinee.getMath());
        value.put(COL_PHYSIC, examinee.getPhysic());
        value.put(COL_CHEMICAL, examinee.getChemical());
        return db.insert(TABLE_NAME, null, value);
    }

    public long update(Examinee examinee) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COL_FULLNAME, examinee.getFullName());
        value.put(COL_IMAGE, examinee.getImage());
        value.put(COL_MATH, examinee.getMath());
        value.put(COL_PHYSIC, examinee.getPhysic());
        value.put(COL_CHEMICAL, examinee.getChemical());
        return db.update(TABLE_NAME,value,COL_ID +"=?",new String[]{String.valueOf(examinee.getId())});
    }

    public void delete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,COL_ID + "=?",new String[]{id});
    }

    public ArrayList<Examinee> getAll() {
        ArrayList<Examinee> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        if (cursor.moveToFirst()) {
            do {
                Examinee e = new Examinee(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getFloat(3),
                        cursor.getFloat(4),
                        cursor.getFloat(5)
                );
                list.add(e);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
