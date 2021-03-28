package com.gestdetaches.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gestdetaches.models.Task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DbWriter  extends DbInit{
    public DbWriter(Context context) {
        super(context);
    }



    /**
     * update a task
     * @param task
     * @return
     */
    public int updateTask(Task task)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITEL, task.getTitel());
        values.put(KEY_DATE, task.getDate());
        values.put(KEY_TIME, task.getTime());
        values.put(KEY_DESCRIPTION, task.getDescription());

        return  db.update(this.TASKS_TABLE , values, KEY_ID+ "=?",
                new String[]{String.valueOf(task.getId())});
    }

    /**
     * to update a task
     * @param task
     */
    public void deleteTask(Task task)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(this.TASKS_TABLE, KEY_ID + "=?",
                new String [] { String.valueOf(task.getId())});
        db.close();
    }


}
