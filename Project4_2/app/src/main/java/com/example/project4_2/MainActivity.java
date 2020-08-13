package com.example.project4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Switch sw;
    RadioGroup rg;
    RadioButton r1, r2, r3;
    ImageView iv;
//    Button btn1;
    Button start,end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사진 보기");

        sw = (Switch) findViewById(R.id.sw);
        tv = (TextView) findViewById(R.id.tv);
        rg = (RadioGroup) findViewById(R.id.rg);
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);
        iv = (ImageView) findViewById(R.id.iv);
//        btn1 = (Button) findViewById(R.id.btn1);
        start = (Button)findViewById(R.id.start);
        end = (Button)findViewById(R.id.end);


        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (sw.isChecked() == true) {
                    tv.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    iv.setVisibility(View.VISIBLE);
                    start.setVisibility(View.VISIBLE);
//                    btn1.setVisibility(View.VISIBLE);
                } else {
                    tv.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.INVISIBLE);
                    start.setVisibility(View.INVISIBLE);
//                    btn1.setVisibility(View.INVISIBLE);
                }
            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setImageResource(R.drawable.a);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setImageResource(R.drawable.b);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setImageResource(R.drawable.m);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sw.setChecked(false);
                iv.setImageResource(0);
                rg.clearCheck();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (rg.getCheckedRadioButtonId()) {
//                    case R.id.r1:
//                        iv.setImageResource(R.drawable.a);
//                        break;
//                    case R.id.r2:
//                        iv.setImageResource(R.drawable.b);
//                        break;
//                    case R.id.r3:
//                        iv.setImageResource(R.drawable.m);
//                        break;
//                    default:
//                        Toast.makeText(getApplicationContext(), "선택을 먼저 하세요.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
}
