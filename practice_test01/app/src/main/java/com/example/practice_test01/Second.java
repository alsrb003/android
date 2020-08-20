package com.example.practice_test01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.219.113:8088/BoardCa/community/Clist.jsp"));
        startActivity(intent);
        overridePendingTransition(0,0);

    }
}
