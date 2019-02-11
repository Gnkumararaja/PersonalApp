package com.kumararaja.personalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "Personal_Expenses.db";
    public static final String TABLE_NAME = " cdtable ";
    public static final String TABLE= " banktable ";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "AMOUNT";
    public static final String COL_3 = "DATEE";
    public static final String COL_4 = "DESCRIPTION";
    public static final String COL_5 = "BANK";
    public static final String COL_6 = "TYPE";

    public static final String TAB_1 ="ID";
    public static final String TAB_2 ="AMOUNT";
    public static final String TAB_3 ="BANK";
    public static final String TAB_4 ="TYPE";



    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, AMOUNT INTEGER, DATEE DATE, DESCRIPTION STRING, BANK STRING, TYPE STRING)");
        db.execSQL("create table" + TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, AMOUNT INTEGER, BANK STRING, TYPE STRING)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE);
    }

    public boolean insertData(String amount, String dob, String description, String bank, String type) {
        Log.v("Checking","InsertMethod");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, amount);
        contentValues.put(COL_3, dob);
        contentValues.put(COL_4, description);
        contentValues.put(COL_5, bank);
        contentValues.put(COL_6, type);
        long result = db.insert(TABLE_NAME, null, contentValues);
        Log.v("Checking","DataValues"+amount+dob+description+bank);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertBank(String amount, String bank, String type) {
        Log.v("Checking","InsertMethod");
        SQLiteDatabase dbb = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(TAB_2, amount);
        contentValue.put(TAB_3, bank);
        contentValue.put(TAB_4, type);
        long result = dbb.insert(TABLE, null, contentValue);
        Log.v("Checking","DataValues"+amount+bank+type);
        if (result == -1)
            return false;
        else
            return true;

    }

    public List<String>getonlyBank(){
        List<String> lt=new ArrayList<String>();
        String sq="SELECT * FROM "+TABLE;
        SQLiteDatabase ddb=this.getReadableDatabase();
        Cursor c= ddb.rawQuery(sq,null);

        if (c.moveToFirst()){
            do {
                lt.add(c.getString(2));
            } while (c.moveToNext());
        }
        c.close();
        ddb.close();

        return lt;
    }

    public List<String>getonlyCash(){
        List<String> lt=new ArrayList<String>();
        String sq="SELECT * FROM "+TABLE;
        SQLiteDatabase ddb=this.getReadableDatabase();
        Cursor c= ddb.rawQuery(sq,null);

        if (c.moveToFirst()){
            do {
                lt.add(c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        ddb.close();

        return lt;
    }

    public boolean checkdb(){
        SQLiteDatabase ddb=this.getReadableDatabase();
        Cursor cursor=ddb.rawQuery("SELECT * FROM "+TABLE,null);
        boolean rowexist;

        if (cursor.moveToFirst()) {
            rowexist = true;
        } else {
            rowexist=false;
        }
        return rowexist;
    }

   /* public List<DCdata> getAllData() {
        List<DCdata> data = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select * from " + TABLE_NAME, null);
        StringBuffer stringBuffer = new StringBuffer();
        DCdata dataModel; //= null;
        if (cursor != null) {
            while (cursor.moveToNext()) {

                String cid=cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                String amnt = cursor.getString(cursor.getColumnIndexOrThrow("AMOUNT"));
                Log.e("DATABASEEEEEEE", "Getting7");
                String dtt = cursor.getString(cursor.getColumnIndexOrThrow("DATEE"));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow("DESCRIPTION"));
                String bnk = cursor.getString(cursor.getColumnIndexOrThrow("BANK"));
                String tpe=cursor.getString(cursor.getColumnIndexOrThrow("TYPE"));
                *//*dataModel.setId(cid);
                dataModel.setAmount(amnt);*//*
                Log.i("Stringgggggggggggggg", amnt);
                *//*dataModel.setDatte(dtt);
                dataModel.setDescrip(desc);
                dataModel.setBankk(bnk);*//*
                dataModel = new DCdata(cid,amnt,dtt,desc,bnk,tpe);
                stringBuffer.append(dataModel);
                data.add(dataModel);
            }

            cursor.close();

        } return data;
    }*/
    public List<BankData> getBankall() {
        List<BankData> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select * from " + TABLE, null);
        StringBuffer stringBuffer = new StringBuffer();
        BankData dataModell; //= null;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String cid=cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                String amnt = cursor.getString(cursor.getColumnIndexOrThrow("AMOUNT"));
                Log.e("DATABASEEEEEEE", "Getting7");
                String bnk = cursor.getString(cursor.getColumnIndexOrThrow("BANK"));
                dataModell=new BankData(cid,amnt,bnk);
                stringBuffer.append(dataModell);
                data.add(dataModell);
            }

            cursor.close();

        } return data;
    }


    public ArrayList<DCdata>getmini(){
        ArrayList<DCdata> dCdata=new ArrayList<>();
        Log.v("","Tab"+dCdata);
        SQLiteDatabase db = this.getWritableDatabase();
      String qq=" SELECT * FROM  "+ TABLE_NAME + " ORDER BY "+COL_3+" DESC";
        Log.e("","Tab"+qq);
      Cursor c=db.rawQuery(qq,null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            // The Cursor is now set to the right position
            dCdata.add(new DCdata(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getString(5)));
        }
            c.close();
        return dCdata;
    };


    /*public ArrayList<BankData>getbankAll(){
        ArrayList<BankData> data=new ArrayList<>();
        Log.v("","Tab"+data);
        SQLiteDatabase db = this.getWritableDatabase();
        String qq=" SELECT * FROM  "+ TABLE + "DESC";
        Log.e("","Tab"+qq);
        Cursor c=db.rawQuery(qq,null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            // The Cursor is now set to the right position
            data.add(new BankData(c.getString()));
        }
        c.close();
        return data;
    };*/


    public boolean updateData(String id,String name, String dob, String email, String cemail, String contact, Blob photo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1,id);
        values.put(COL_2, name);
        values.put(COL_3, dob);
        values.put(COL_4, email);
        values.put(COL_5, cemail);
        db.update(TABLE_NAME, values, "ID = ?" , new String[] { id });
        return true;
    }

   /* public boolean updateData1(String id,String name, String dob, String email, String cemail, String contact, Blob photo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1,id);
        values.put(COL_2, name);
        values.put(COL_3, dob);
        values.put(COL_4, email);
        values.put(COL_5, cemail);
        db.update(TABLE_NAME1, values, "ID = ?" , new String[] { id });
        return true;
    }*/

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

   /* public Integer deleteData1 (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "ID = ?",new String[] {id});
    }*/

}
