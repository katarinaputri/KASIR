package com.example.kasir;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_kasir";

    //Buat conctractor baru
    public Database (Context context){
        super(context, DB_NAME, null,1);
    }

//    public static final String TB_USER = "user";
//    public static final String COL_ID = "id";
//    public static final String COL_EMAIL = "email";
//    public static final String COL_USERNAME = "username";
//    public static final String COL_PASSWORD = "password";
//
//    public String user = "create table " + TB_USER + "(" +
//            COL_ID + " integer primary key autoincrement, " +
//            COL_EMAIL + " text, " +
//            COL_USERNAME + " text, " +
//            COL_PASSWORD + " text);";
//
//    public static final String TB_MENU ="menu";
//    //colom id sama kaya tabel sebelumnya
//    public static final String COL_MENU = "nama";
//    public static final String COL_HARGA = "harga";
//
//    public String menu = "create table " + TB_MENU + "(" +
//            COL_ID + " integer primary key autoincrement, " +
//            COL_MENU + " text, " +
//            COL_HARGA + " text);";

    @Override
    public void onCreate(SQLiteDatabase db) {

//        String sql = "create table user (id integer primary key autoincrement, email text null, username text null, password text null);";
//        Log.d("Data", "onCreate: " +sql);
//        db.execSQL(sql);

//        sql = "INSERT INTO user VALUES" +
//                "('', 'admin@gmail.com', 'admin', 'admin123')";
//        db.execSQL(sql);

        String sql2 = "create table menu (id integer primary key, menu text null, harga text null);";
        Log.d("Data", "onCreate: " +sql2);
        db.execSQL(sql2);

        sql2 = "INSERT INTO menu (id, menu, harga) VALUES ('NG', 'Nasi Goreng', '15000')";
        db.execSQL(sql2);

        String sql3 = "create table laporan (id integer primary key, namapembeli text null, totalbelanja text null, totalbayar text null, kembalian text null);";
        Log.d ("Data", "onCreate: " + sql3);
        db.execSQL(sql3);

        sql3 = "INSERT INTO laporan (id, namapembeli, totalbelanja, totalbayar, kembalian) VALUES ('1', 'dita', '23000', '25000', '2000')";
        db.execSQL(sql3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}
