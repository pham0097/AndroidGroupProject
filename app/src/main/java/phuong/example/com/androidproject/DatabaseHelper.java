package phuong.example.com.androidproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

  protected static final String ACTIVITY_NAME = "KitchenRemote";
  public static String DATABASE_NAME = "kitechremote.db";
  public static Integer VERSION_NUM = 9;
  public static String TABLE_NAME = "AddTable";
  public final static String KEY_ID = "ID";
  public final static String KEY_TYPE = "ITEMS";
  public final static String KEY_NAME = "NAME";
  public final static String KEY_SETTINGS = "SETTINGS";

  public DatabaseHelper(Context ctx) {
    super(ctx, DATABASE_NAME, null, VERSION_NUM);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE "
        + TABLE_NAME
        + " ("
        + KEY_ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + KEY_TYPE
        + " TEXT"
        + KEY_NAME
        + "TEXT"
        + KEY_SETTINGS
        + "TEXT");

    Log.i("DatabaseHelper", "Calling onCreate");
  }

  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
    Log.i(ACTIVITY_NAME,
        "Calling onUpgrade: Older Version: " + oldVersion + " New Version : " + newVersion);
  }

  public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);

    Log.i("DatabaseHelper",
        "Calling onDowngrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
  }
}

