package android.example.gurbir_calorie_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "foodTable";
    static final String CALORIE_COLUMN = "Calorie";
    static final String PROTEIN_COLUMN = "Protein";
    static final String FOODNAME_COLUMN = "FoodName";
    static final String CARB_COLUMN = "Carbs";;
    private static final String COL1 = "ID";


    public DatabaseHelper (Context context) {

        super(context, TABLE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FOODNAME_COLUMN + " TEXT," + CALORIE_COLUMN + " TEXT,"
                + PROTEIN_COLUMN + " TEXT," + CARB_COLUMN + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String foodname, String calories, String protein, String carbs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOODNAME_COLUMN, foodname);
        contentValues.put(CALORIE_COLUMN, calories);
        contentValues.put(PROTEIN_COLUMN, protein);
        contentValues.put(CARB_COLUMN, carbs);


        Log.d(TAG, "addData: Adding " + foodname + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, COL1, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}

