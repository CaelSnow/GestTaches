package com.gestdetaches.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gestdetaches.models.Task;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class DbInit extends SQLiteOpenHelper
{
    protected static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "TaskStora";
    protected static final String TASKS_TABLE = "MyTask";
    protected static final String KEY_ID = "id";
    protected static final String KEY_TITEL = "titel";
    protected static final String KEY_DATE ="date";
    protected static final String KEY_TIME ="time";
    protected static final String KEY_DESCRIPTION ="description";

    public DbInit(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creation de la table
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TASKS_TABLE = "CREATE TABLE "+TASKS_TABLE+"("
                +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_TITEL+" TEXT, "+
                KEY_DATE+" TEXT,"+KEY_TIME+" TEXT, "+KEY_DESCRIPTION+" TEXT"+
                " );";
        db.execSQL(CREATE_TASKS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //
        onCreate(db);
    }

    /**
     *  add new task on the table.
     */
    public void addTaskOnDb(Task task)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITEL, task.getTitel());
        values.put(KEY_DATE, task.getDate());
        values.put(KEY_TIME, task.getTime());
        values.put(KEY_DESCRIPTION, task.getDescription());
        //Inserting Row
        db.insert(TASKS_TABLE, null, values);
        db.close();
    }

}
