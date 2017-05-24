package phuong.example.com.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 3;


         static final String DATABASE_NAME = "ODOMETER.db";
         static final String TABLE = "TRIP";

        // notes Table Columns names
        static final String COLUAM2 = "ID";
         static final String COLUAM1 = "DISTANCE";


        public Database(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                String CREATE_NOTE_TABLE = "CREATE TABLE "
                        + TABLE
                        + "("
                        + COLUAM2
                        + " INTEGER PRIMARY KEY, "
                        + COLUAM1
                        + " INTEGER"
                        + ")";
                db.execSQL(CREATE_NOTE_TABLE);
                ContentValues values = new ContentValues();
                values.put(COLUAM1, "0");
                db.insert(TABLE,null,values);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE);
                onCreate(db);
        }
        public Cursor getDistance(){
                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.query(true,TABLE,new String[] {COLUAM1},null,null,null,null,null,null);
                return cursor;


                // Inserting Row

        }
        public void setDistance(String s){
                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.query(true,TABLE,new String[] {COLUAM1},null,null,null,null,null,null);
                cursor.moveToFirst();
                String current  = cursor.getString(cursor.getColumnIndex(COLUAM1));

                ContentValues values = new ContentValues();
                values.put(COLUAM1, s);


                // Inserting Row
                db.update(TABLE,values, COLUAM1 + " = ?" , new String[] {current});
                db.close();
        }
}