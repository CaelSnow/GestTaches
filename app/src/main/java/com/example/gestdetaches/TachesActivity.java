package com.example.gestdetaches;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gestdetaches.dbUtils.DbReader;
import com.gestdetaches.dbUtils.DbWriter;
import com.gestdetaches.models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TachesActivity extends AppCompatActivity {



    private Button save ;
    private EditText titel;
    private TextView date;
    private TextView time;
    private DatePickerDialog.OnDateSetListener DateSetListenner;
    private DbWriter dataWriter;
    private DbReader dataReader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taches);

        save = (Button) findViewById(R.id.save);
        titel=(EditText) findViewById(R.id.edittitel);
        date=(TextView) findViewById(R.id.editdate);
        time=(TextView) findViewById(R.id.edittime);
        dataWriter = new DbWriter(this);
        dataReader = new DbReader(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(TachesActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,DateSetListenner,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        DateSetListenner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month +=1;
                Log.d("TAG","onDateSet: dd/mm/yyyy: " + dayOfMonth + "/" + month + "/" + year);
                date.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] heure = new int[1];
                int minute;
                TimePickerDialog timePickerDialog = new TimePickerDialog(TachesActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        heure[0] = hourOfDay;
                        minute = minute;

                        String temps = heure[0] + ":" + minute;

                        // initialiser le format 24h
                        SimpleDateFormat f24hours = new SimpleDateFormat("HH:MM");
                        try {
                            Date date = f24hours.parse(temps);
                            //initialiser le format 12h
                            SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                            time.setText(f12hours.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },12,0,false);
                //set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent previousActivity = new Intent(getApplicationContext(),MainActivity.class);
                Task tache = new Task(titel.getText().toString(),date.getText().toString(),
                        time.getText().toString());
                dataWriter.addTaskOnDb(tache);
                previousActivity.putExtra("titel",tache.getTitel());
                previousActivity.putExtra("date",tache.getDate());
                previousActivity.putExtra("time",tache.getTime());
                setResult(1,previousActivity);

                 finish();
            }
        });
    }
}