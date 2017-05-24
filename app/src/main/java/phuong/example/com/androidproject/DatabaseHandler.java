package phuong.example.com.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

  // All Static variables
  // Database Version
  private static final int DATABASE_VERSION = 9;

  // Database Name
  private static final String DATABASE_NAME = "KitchenRemote";

  // notes table name
  private static final String TABLE_DEVICES = "devices";

  // notes Table Columns names
  private static final String KEY_ID = "id";
  private static final String KEY_NAME = "name";
  private static final String KEY_TYPE = "type";
  private static final String KEY_SETTING = "setting";

  public DatabaseHandler(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  // Creating Tables
  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_NOTE_TABLE = "CREATE TABLE "
        + TABLE_DEVICES
        + "("
        + KEY_ID
        + " INTEGER PRIMARY KEY,"
        + KEY_NAME
        + " TEXT,"
        + KEY_TYPE
        + " TEXT,"
        + KEY_SETTING
        + " TEXT"
        + ")";
    db.execSQL(CREATE_NOTE_TABLE);
  }

  // Upgrading database
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVICES);

    // Create tables again
    onCreate(db);
  }

  /**
   * All CRUD(Create, Read, Update, Delete) Operations
   */

  // Adding new device
  void addDevice(Device note) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_NAME, note.getName());
    values.put(KEY_TYPE, note.getType());
    values.put(KEY_SETTING, note.getSetting());

    // Inserting Row
    db.insert(TABLE_DEVICES, null, values);
    db.close(); // Closing database connection
  }

  // Getting single one
  Device getDevice(int id) {
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.query(TABLE_DEVICES, new String[] {
        KEY_ID, KEY_NAME, KEY_TYPE, KEY_SETTING
    }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
    if (cursor != null) cursor.moveToFirst();

    Device note =
        new Device(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
            cursor.getString(3));
    // return note
    return note;
  }

  // Getting All
  public List<Device> getAllDevices() {
    List<Device> noteList = new ArrayList<Device>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + TABLE_DEVICES;

    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
      do {
        Device note = new Device();
        note.set_id(Integer.parseInt(cursor.getString(0)));
        note.setName(cursor.getString(1));
        note.setType(cursor.getString(2));
        note.setSetting(cursor.getString(3));

        // Adding note to list
        noteList.add(note);
      } while (cursor.moveToNext());
    }

    // return note list
    return noteList;
  }

  // Updating single note
  public int updateDevice(Device note) {

    Log.e(String.valueOf(note.get_id()), "update in db");
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_NAME, note.getName());
    values.put(KEY_TYPE, note.getType());
    values.put(KEY_SETTING, note.getSetting());

    // updating row
    return db.update(TABLE_DEVICES, values, KEY_ID + " = ?",
        new String[] { String.valueOf(note.get_id()) });
  }

  // Deleting single note
  public void deleteDevice(Device note) {
    SQLiteDatabase db = this.getWritableDatabase();

    db.delete(TABLE_DEVICES, KEY_ID + " = ?", new String[] { String.valueOf(note.get_id()) });
    db.close();
  }

  // Getting notes Count
  public int getDeviceCount() {
    String countQuery = "SELECT  * FROM " + TABLE_DEVICES;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);
    cursor.close();

    // return count
    return cursor.getCount();
  }
}
