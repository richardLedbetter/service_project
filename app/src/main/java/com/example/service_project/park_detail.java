package com.example.service_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;

import java.util.ArrayList;

public class park_detail extends AppCompatActivity {
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_detail);
        ll = findViewById(R.id.park_d_ll);
        ArrayList<Button> issues = new ArrayList<>();
        //View Screen = findViewByI;

        for (int i =0;i<5;i++){
           // issues.add(new Button(this));
           // PopupMenu popup = new PopupMenu(this, Screen);
           // MenuInflater inflater = popup.getMenuInflater();
           // inflater.inflate(R.menu.menu_example, popup.getMenu());
            //popup.show();
            ll.addView(new Button(this));
        }
    }

}
