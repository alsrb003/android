package com.example.project5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2;
    TextView tv;
    Button add, sub, mul, div;
    Button[] numButtons = new Button[10];
    Integer numBtnIDs[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("테이블 레이아웃 계산기");

        ed1 = (EditText) findViewById(R.id.edit1);
        ed2 = (EditText) findViewById(R.id.edit2);

        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        tv = (TextView) findViewById(R.id.tv);

        for (int i = 0; i < numButtons.length; i++) {
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }
        for (int i = 0; i < numButtons.length; i++) {
            final int index = i;
            numButtons[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(ed1.isFocused() == true){
                        ed1.setText(ed1.getText().toString() + numButtons[index].getText().toString());
                    }else if(ed2.isFocused() == true){
                        ed2.setText(ed2.getText().toString() + numButtons[index].getText().toString());
                    }else{
                        Toast.makeText(getApplicationContext(),"먼저 에디트 텍스트를 입력하세요.",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }




        add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tv.setText("계산 결과 : " + (Integer.parseInt(ed1.getText().toString())+Integer.parseInt(ed2.getText().toString())));
                return false;
            }
        });
        sub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tv.setText("계산 결과 : " + (Integer.parseInt(ed1.getText().toString())-Integer.parseInt(ed2.getText().toString())));
                return false;
            }
        });
        mul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tv.setText("계산 결과 : " + (Integer.parseInt(ed1.getText().toString())*Integer.parseInt(ed2.getText().toString())));
                return false;
            }
        });
        div.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tv.setText("계산 결과 : " + (Integer.parseInt(ed1.getText().toString())/Integer.parseInt(ed2.getText().toString())));
                return false;
            }
        });


    }
}
