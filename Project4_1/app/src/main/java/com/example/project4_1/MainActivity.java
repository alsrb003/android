package com.example.project4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button btn1,btn2,btn3,btn4,btn5;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setTextColor(Color.RED);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et1.getText().toString().equals("") || et2.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(),"입력 값이 비엇습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    double n1 = Double.parseDouble(et1.getText().toString());
                    double n2 = Double.parseDouble(et2.getText().toString());
                    tv1.setText("계산 결과: " + (n1 + n2));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et1.getText().toString().equals("") || et2.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(),"입력 값이 비엇습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    double n1 = Double.parseDouble(et1.getText().toString());
                    double n2 = Double.parseDouble(et2.getText().toString());
                    tv1.setText("계산 결과: " + (n1 - n2));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et1.getText().toString().equals("") || et2.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(),"입력 값이 비엇습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    double n1 = Double.parseDouble(et1.getText().toString());
                    double n2 = Double.parseDouble(et2.getText().toString());
                    tv1.setText("계산 결과: " + (n1 * n2));
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et1.getText().toString().equals("") || et2.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(),"입력 값이 비엇습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    if(et2.getText().toString().equals("0")){
                        Toast.makeText(getApplicationContext(),"0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                        tv1.setText("계산 결과: ");
                    }else {
                        double n1 = Double.parseDouble(et1.getText().toString());
                        double n2 = Double.parseDouble(et2.getText().toString());
                        tv1.setText("계산 결과: " + (n1 / n2));
                    }
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et1.getText().toString().equals("") || et2.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(),"입력 값이 비엇습니다.", Toast.LENGTH_SHORT).show();
                }else {
                        double n1 = Double.parseDouble(et1.getText().toString());
                        double n2 = Double.parseDouble(et2.getText().toString());
                        tv1.setText("계산 결과: " + (n1 % n2));
                }
            }
        });
    }
}
