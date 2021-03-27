package com.example.gestdetaches;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class MainActivity extends AppCompatActivity {

      private static final  int Second_Call_ID = 1234;
      private Button moretaks;
      private  TextView tacheView;
      private ListView listView;
      public static final String SHARED_PREF = "sharedpref";
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         moretaks = (Button) findViewById(R.id.moretaks);
        listView = (ListView) findViewById(R.id.listView);

         moretaks.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent tachesActivity = new Intent(getApplicationContext(),TachesActivity.class);
                 startActivityForResult(tachesActivity,Second_Call_ID);
             }
         });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent previousActivity) {
        super.onActivityResult(requestCode, resultCode, previousActivity );



        if(requestCode == Second_Call_ID){

            if(resultCode == 1 || resultCode ==3 ) {

                Tache tache = new Tache(previousActivity.getStringExtra("titel"),previousActivity.getStringExtra("date"),previousActivity.getStringExtra("time"));




                arrayList.add(tache.afficherTache());

                ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(arrayAdapter);



                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent modifTache = new Intent(MainActivity.this,ModifiicationActivity.class);
                        modifTache.putExtra("titel",tache.titel);
                        modifTache.putExtra("date",tache.date);
                        modifTache.putExtra("time",tache.time);
                           startActivityForResult(modifTache,Second_Call_ID);

                    }
                });

            }
            }


    }



}