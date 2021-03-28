package com.gestdetaches.dbUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gestdetaches.models.Task;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DbReader extends DbInit
{


    public DbReader(Context context) {
        super(context);
    }

    /**
     * get one Task on  the database by the id.
     */
    public  Task getTaskById(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TASKS_TABLE, new String[]{ KEY_ID, KEY_TITEL, KEY_DATE,
                        KEY_TIME, KEY_DESCRIPTION}, KEY_ID + " =?", new String[]{String.valueOf(id)},
                null,null, null, null);
        if (cursor!= null)
        {
            cursor.moveToFirst();
            Task task = new Task(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4));
            return task;
        }
        return null;
    }

    /**
     * get all task
     * @return a list of <b>Task</b>
     */
    public  List<Task> getAllTasks()
    {
        List<Task> tasksList = new ArrayList<Task>();

        //select all Query
        String selectQuery = "SELECT * FROM "+super.TASKS_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do{
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTitel(cursor.getString(1));
                task.setDate(cursor.getString(2));
                task.setTime(cursor.getString(3));
                task.setDescription(cursor.getString(4));
                tasksList.add(task);
            }while (cursor.moveToNext());
        }
        return tasksList;
    }

    /**
     * get the count of tasks
     * @return
     */
    public int getTaskCount()
    {
        String countQuery = "SELECT * FROM "+ TASKS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
}
