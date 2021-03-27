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
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ModifiicationActivity extends AppCompatActivity {


    private static final  int Second_Call_ID3 = 1233;
    private EditText modifTitel;
    private Button modifDate;
    private Button modifTime;
    private  Button newSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifiication);

        String titel = this.getIntent().getExtras().getString("titel");
        String date = this.getIntent().getExtras().getString("date");
        String time = this.getIntent().getExtras().getString("time");

        modifTitel =(EditText) findViewById(R.id.modifTitel);
        modifDate = (Button) findViewById(R.id.modifDate);
        modifTime = (Button) findViewById(R.id.modifTime);
        newSave = (Button) findViewById(R.id.newSave);




        modifTitel.setText(titel);
        modifDate.setText(date);
        modifTime.setText(time);

        modifDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ModifiicationActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                month +=1;
                                Log.d("TAG","onDateSet: dd/mm/yyyy: " + dayOfMonth + "/" + month + "/" + year);
                                modifDate.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }
                        ,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });



        modifTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] heure = new int[1];
                int minute;
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifiicationActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
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
                            modifTime.setText(f12hours.format(date));
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

        newSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent previousActivity = new Intent(getApplicationContext(),MainActivity.class);

                previousActivity.putExtra("titel",modifTitel.getText().toString());
                previousActivity.putExtra("date",modifDate.getText().toString());
                previousActivity.putExtra("time",modifTime.getText().toString());

                setResult(3 ,previousActivity);

                finish();
            }
        });
    }
}