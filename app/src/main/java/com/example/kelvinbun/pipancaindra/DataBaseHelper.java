package com.example.kelvinbun.pipancaindra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class DataBaseHelper extends SQLiteOpenHelper {
    private Context mycontext;

    private static String DB_NAME = "penulisan.db";
    private static String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases/";
    public SQLiteDatabase myDataBase;

    public DataBaseHelper(Context context) throws IOException {
        super(context, DB_NAME, null, 1);
        this.mycontext = context;
        boolean dbexist = checkdatabase();
        if (dbexist) {
            System.out.println("Database exists");
            opendatabase();
        } else {
            System.out.println("Database doesn't exist");
            createdatabase();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if (dbexist) {
            System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        Log.v("TEST", "0");
        InputStream myinput = mycontext.getAssets().open(DB_NAME);
        Log.v("TEST", "1");
        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;
        Log.v("TEST", "2");
        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);
        Log.v("TEST", "3");
        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer)) > 0) {
            myoutput.write(buffer, 0, length);
        }
        Log.v("TEST", "4");
        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    public List<String> getpancaindra(int type) { // Untuk Mengambil semua judul dalam note
        ArrayList<String> pancaindra = new ArrayList<String>();
        String selectQuery = "SELECT * FROM  pancaindra where kode='" + type + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                pancaindra.add(cursor.getString(1));// gambar
                pancaindra.add(cursor.getString(2));// penjelasan1
                pancaindra.add(cursor.getString(3));//penjelasan2
                pancaindra.add(cursor.getString(4));//penjelasan3
                pancaindra.add(cursor.getString(5));//video
            } while (cursor.moveToNext());
        }
        return pancaindra;
    }

    public List<String> getsoal(int nosoal) { // Untuk Mengambil semua judul dalam note
        ArrayList<String> soal = new ArrayList<String>();
        String selectQuery = "SELECT * FROM  kuis where id='" + nosoal + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                soal.add(cursor.getString(cursor.getColumnIndex("soal")));
                soal.add(cursor.getString(cursor.getColumnIndex("pil1")));
                soal.add(cursor.getString(cursor.getColumnIndex("pil2")));
                soal.add(cursor.getString(cursor.getColumnIndex("pil3")));
                soal.add(cursor.getString(cursor.getColumnIndex("pil4")));
                soal.add(cursor.getString(cursor.getColumnIndex("jwban")));
            } while (cursor.moveToNext());

    }
            return soal;
    }
    /**public List<String> getjwban(int nosoal) { // Untuk Mengambil semua judul dalam note
        ArrayList<String> soal = new ArrayList<String>();
        String selectQuery = "SELECT * FROM  kuis where id='" + nosoal + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                soal.add(cursor.getString(2));
                soal.add(cursor.getString(3));
                soal.add(cursor.getString(4));
                soal.add(cursor.getString(5));
                soal.add(cursor.getString(6));

            } while (cursor.moveToNext()) ;
        }
        return soal;
    }**/
}


