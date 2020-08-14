package com.example.project6_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chorono;
    Button btn1,btn2;
    RadioButton rb1,rb2;
    CalendarView cv;
    TimePicker tp;
    TextView year,month,day,hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");

        chorono = (Chronometer)findViewById(R.id.chrono);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        rb1 = (RadioButton)findViewById(R.id.rb1);
        rb2 = (RadioButton)findViewById(R.id.rb2);
        cv = (CalendarView)findViewById(R.id.cv);
        tp = (TimePicker) findViewById(R.id.tp);
        year = (TextView) findViewById(R.id.year);
        month = (TextView) findViewById(R.id.month);
        day = (TextView) findViewById(R.id.day);
        hour = (TextView) findViewById(R.id.hour);
        minute = (TextView) findViewById(R.id.minute);

        tp.setVisibility(View.INVISIBLE);
        cv.setVisibility(View.INVISIBLE);

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tp.setVisibility(View.INVISIBLE);
                cv.setVisibility(View.VISIBLE);
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tp.setVisibility(View.VISIBLE);
                cv.setVisibility(View.INVISIBLE);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chorono.setBase(SystemClock.elapsedRealtime());
                chorono.start();
                chorono.setTextColor(Color.RED);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chorono.stop();
                chorono.setTextColor(Color.BLUE);
            }
        });

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                year.setText(Integer.toString(i));
                month.setText(Integer.toString(i1));
                day.setText(Integer.toString(i2));

                hour.setText(Integer.toString(tp.getCurrentHour()));
                minute.setText(Integer.toString(tp.getCurrentMinute()));
            }
        });

    }
}
