package www.pbergsproductions.taskkeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String TAG = "DBHandler";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "TasksDB";
    private static final String TABLE_TASKS = "tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";
    private static final String KEY_DATE = "date";
    private static final String KEY_PRIORITY = "priority";

    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    /*
    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }/* */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "Create Table " + TABLE_TASKS + "(" + KEY_ID + " Integer Primary Key, "
                + KEY_NAME + " Text, " + KEY_DESC + " Text" + ")";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: Database being upgraded.");

        db.execSQL("DROP TABLE If Exists " + TABLE_TASKS);
        onCreate(db);

    }


}
