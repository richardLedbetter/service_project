package com.example.service_project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;

import com.example.service_project.Model.Park;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class park_detail extends AppCompatActivity {

    private DatabaseReference mDatabase;
// ...



    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_detail);
        ll = findViewById(R.id.park_d_ll);

        //used to free up UI thread
        Runnable R = new Runnable(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                create();
            }
        };
        Thread task1 = new Thread(R);
        task1.start();
        //end Threaded tasks
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void create(){
        //Generateing layout
        Button Add_issue = new Button(this);
        Add_issue.setHeight(250);
        Add_issue.setText("ADD ISSUE");
        Add_issue.setOnClickListener(v1->{
            Intent intent = new Intent(this,add_issue.class);
            startActivity(intent);
        });
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                ll.addView(Add_issue);
            }
        };
        runOnUiThread(task1);
        for (int i =0;i<5;i++){
            //issues button
            Button issue = new Button(this);
            issue.setText("Issue"+Integer.toString(i));
            issue.setHeight(225);
            //drop down menu
            LinearLayout content = new LinearLayout(this);
            content.setOrientation(LinearLayout.VERTICAL);
            //drop down menu content
            Button issue2 = new Button(this);

            issue2.setWidth(5000);
            issue2.setBottom(100);


            issue2.setText("fix");
            issue2.setOnClickListener(v1->{
                issue2.setVisibility(LinearLayout.GONE);
                issue.setVisibility(LinearLayout.GONE);
                content.setVisibility(LinearLayout.GONE);
            });

            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(584, 584);


            ImageView im = new ImageView(this);

            Runnable t = new Runnable() {
                @Override
                public void run() {
                    LinearLayout.LayoutParams layoutParams =
                            new LinearLayout.LayoutParams(584, 584);
                    im.setImageResource(R.drawable.parksample1);
                    content.addView(im,layoutParams);
                    content.addView(issue2);

                }
            };

            runOnUiThread(t);

            //drop down menu action
            issue.setOnClickListener(v1->{
                if(content.getVisibility()==LinearLayout.GONE){
                    content.setVisibility(LinearLayout.VISIBLE);
                    //Intent next_page = new Intent(this,time_card.class);
                    //startActivity(next_page);
                }else{
                    content.setVisibility(LinearLayout.GONE);
                }

            });
            content.setVisibility(LinearLayout.GONE);
            //end drop down menu action

            Runnable add_to_screen = ()->{
                ll.addView(issue);
                ll.addView(content);
            };
            runOnUiThread(add_to_screen);

        }
    }

}
