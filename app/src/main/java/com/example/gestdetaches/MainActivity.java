package com.example.gestdetaches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gestdetaches.dbUtils.DbReader;
import com.gestdetaches.models.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainActivity extends AppCompatActivity {

      private static final  int Second_Call_ID = 1234;
      private FloatingActionButton moretaks;
      private  TextView tacheView;
      private ListView listView;
      private ArrayAdapter listAdapter;
      public static final String SHARED_PREF = "sharedpref";

    private DbReader dbReader;
    ArrayList<String> myTasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         moretaks = (FloatingActionButton ) findViewById(R.id.moretaks);
        listView = (ListView) findViewById(R.id.listView);

         moretaks.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent tachesActivity = new Intent(getApplicationContext(),TachesActivity.class);
                 startActivityForResult(tachesActivity,Second_Call_ID);
             }
         });

             dbReader = new DbReader(this);
             List<Task> taskFromDB = dbReader.getAllTasks();
             if (dbReader.getAllTasks().size() != 0)
             {
                 for(Task tasks : dbReader.getAllTasks())
                 {
                     myTasks.add(tasks.toString());
                 }
                 listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, myTasks);
                 listView.setAdapter(listAdapter);
             }
             else
             {
                 System.out.println("NO TASKS ON THE DB");
             }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent previousActivity) {
        super.onActivityResult(requestCode, resultCode, previousActivity );

        if(requestCode == Second_Call_ID){

            if(resultCode == 1 || resultCode ==3 ) {
                Task tache = new
                        Task(previousActivity.getStringExtra("titel"),previousActivity.getStringExtra("date"),
                        previousActivity.getStringExtra("time"));
                updateListView(tache);

             }
            }
    }

    private void updateListView(Task tache)
    {
        myTasks.add(tache.toString());
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myTasks);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent modifTache = new Intent(MainActivity.this,ModifiicationActivity.class);
                modifTache.putExtra("titel",tache.getTitel());
                modifTache.putExtra("date",tache.getDate());
                modifTache.putExtra("time",tache.getTime());
                startActivityForResult(modifTache,Second_Call_ID);

            }
        });

    }

    private void initListView(Task tache)
    {


    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}