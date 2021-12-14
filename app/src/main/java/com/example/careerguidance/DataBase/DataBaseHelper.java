package com.example.careerguidance.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    String DB_NAME;
    String DB_PATH;
    SQLiteDatabase myDatabase;
    private Context myContext;

    public DataBaseHelper(Context context, String name, int version){
        super(context, name, null, version);
        this.myContext = context;
        this.DB_PATH = "/data/data/com.example.careerguidance/databases/";
        this.DB_NAME = name;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void checkDatabase(){
        SQLiteDatabase checkDb = null;
        String filePath = DB_PATH + DB_NAME;
        try{
            checkDb = SQLiteDatabase.openDatabase(filePath,null,0);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (checkDb != null){
        }else{
            copyDatabase();
        }
    }
    public void copyDatabase(){
        this.getReadableDatabase();
        try{
            InputStream inputStream = myContext.getAssets().open(DB_NAME);
            OutputStream outputStream = new FileOutputStream(DB_PATH + DB_NAME);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("CopyDB", "Database copied!");
    }
    public void openDatabase(){
        String filePath = DB_PATH + DB_NAME;
        SQLiteDatabase.openDatabase(filePath, null,0);
    }
    public void loadHandler(String personality, ArrayList<String> jobTitle, ArrayList<String> localUniversity, ArrayList<String> degreeProgram){
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+personality, null);
        while (c.moveToNext()){
            jobTitle.add(c.getString(1) + " " +c.getString(2));
            localUniversity.add(c.getString(3));
            degreeProgram.add(c.getString(4));
        }
        c.close();
        db.close();
    }
}

