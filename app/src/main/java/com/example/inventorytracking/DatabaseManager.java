package com.example.inventorytracking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "flowerDB";
    private static final int DATABASE_VERSION =1;
    private static final String TABLE_FLOWER = "flower";

    private static final String ID = "id";
    private static final String SCHOOL = "school";
    private static final String DATE = "date";
    private static final String BOOTH = "booth";
    private static final String PRODUCT = "product";
    private static final String AMOUNT = "amount";

    public DatabaseManager(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_FLOWER + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + SCHOOL;
        sqlCreate += " text, " + DATE + " text, ";
        sqlCreate +=  BOOTH + " text, ";
        sqlCreate += PRODUCT + " text, ";
        sqlCreate += AMOUNT + " text )";

        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL( "drop table if exists " + TABLE_FLOWER);
        onCreate( db);

    }
    public void insert(Product product){

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_FLOWER;
        sqlInsert += " values( null, '" +  product.getSchoolName();
        sqlInsert += "', '" + product.getNumOfBooths();
        sqlInsert += "', '" + product.getDate();
        sqlInsert += "', '" + product.getProductSold();
        sqlInsert += "', '" + product.getProductAmounnt() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }
}
