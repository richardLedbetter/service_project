package com.example.service_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
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
            @Override
            public void run() {
                create();
            }
        };
        Thread task1 = new Thread(R);
        task1.start();
        //end Threaded tasks
    }

    public void create(){
        //Generateing layout
        for (int i =0;i<5;i++){
            //issues button
            Button issue = new Button(this);
            //drop down menu
            LinearLayout content = new LinearLayout(this);
            //drop down menu content
            Button issue2 = new Button(this);
            issue2.setWidth(5000);
            ImageView im = new ImageView(this);

            //im.setImageBitmap(Image);
            issue2.setText("fix");
            content.addView(issue2);

            //drop down menu action
            issue.setOnClickListener(v1->{
                if(content.getVisibility()==LinearLayout.GONE){
                    content.setVisibility(LinearLayout.VISIBLE);
                    Intent next_page = new Intent(this,time_card.class);
                    startActivity(next_page);
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
