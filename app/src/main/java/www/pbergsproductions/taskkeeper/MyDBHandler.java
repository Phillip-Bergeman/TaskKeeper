package www.pbergsproductions.taskkeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final String TAG = "MyDBHandler";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "taskDB";
    private static final String TABLE_TASKS = "tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DUEDATE = "date";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_DESC = "description";

    public MyDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: Table is being created.");
        String createQuery = "Create Table " + TABLE_TASKS + "(" + KEY_ID +
                " Integer Primary Key, " + KEY_NAME + " Text," + KEY_DUEDATE +
                " Text, " + KEY_PRIORITY + " Integer, " + KEY_DESC +
                " Text" + ")";

        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: Database being upgraded.");
        db.execSQL("Drop Table If Exists " + TABLE_TASKS);
        onCreate(db);
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DUEDATE, task.getDueDate());
        values.put(KEY_PRIORITY, task.getPriority());
        values.put(KEY_DESC, task.getDescription());

        db.insert(TABLE_TASKS, null, values);

        db.close();
    }

    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASKS, new String[]{KEY_ID, KEY_NAME, KEY_DUEDATE, KEY_PRIORITY, KEY_DESC},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            Task task = new Task(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getString(2), Integer.parseInt(cursor.getString(3)),
                    cursor.getString(4));

            return task;
        } else {
            return null;
        }
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<Task>();

        String selectQuery = "SELECT * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setDueDate(cursor.getString(2));
                task.setPriority(Integer.parseInt(cursor.getString(3)));
                task.setDescription(cursor.getString(4));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }

    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DUEDATE, task.getDueDate());
        values.put(KEY_PRIORITY, task.getPriority());
        values.put(KEY_DESC, task.getDescription());

        return db.update(TABLE_TASKS, values, KEY_ID + " = ?",
                new String[] {String.valueOf(task.getId())});
    }

    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " = ?",
                new String[] {String.valueOf(task.getId())});
    }

    public int getTaskCount() {
        String countQuery = "SELECT * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

}
