package com.example.service_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");


    LinearLayout ll;
    int ScreenHeight,ScreenWidth;
    DisplayMetrics displayMetrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myRef.setValue("Hello, World!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = findViewById(R.id.ll);
        displayMetrics = this.getResources().getDisplayMetrics();
        ScreenWidth = (int) (displayMetrics.widthPixels);
        ScreenHeight = (int) (displayMetrics.heightPixels );

        set_up();
    }
    public void set_up(){
        Button Time_card = new Button(this);
        Time_card.setText("TIME CARD");
        Time_card.setHeight(ScreenHeight/4);
        Time_card.setOnClickListener(v1->{
            Intent next_page = new Intent(this,time_card.class);
            startActivity(next_page);
        });
        ll.addView(Time_card);
        for (int i =0;i<10;i++){
            Button b = new Button(this);
            b.setText("Park"+ Integer.toString(i));
            b.setHeight(ScreenHeight/5);
            b.setOnClickListener(v1->{
                Intent next_page = new Intent(this,park_detail.class);
                startActivity(next_page);
            });
            ll.addView(b);
        }


    }
}
