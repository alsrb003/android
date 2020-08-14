package com.example.autocomplatetextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = {"CSI-뉴욕", "CSI-마이애미", "CSI-라스베가스", "Friends", "Fringe", "Lost"};
        AutoCompleteTextView at1 = (AutoCompleteTextView) findViewById(R.id.at1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        at1.setAdapter(adapter);

        MultiAutoCompleteTextView at2 = (MultiAutoCompleteTextView) findViewById(R.id.at2);
        MultiAutoCompleteTextView.CommaTokenizer token = new MultiAutoCompleteTextView.CommaTokenizer();
        at2.setTokenizer(token);
        at2.setAdapter(adapter);
    }
}
