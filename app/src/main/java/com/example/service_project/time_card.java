package com.example.service_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class time_card extends AppCompatActivity {
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_card);
        ll = findViewById(R.id.form);
        pop();
    }
    public void pop(){
        TextView t = new TextView(this);
        t.setText("enter employee code");
        ll.addView(t);

        //ID
        EditText ID = new EditText(this);
        ll.addView(ID);

        //signing in or out
        TextView Radio = new TextView(this);
        Radio.setText("Signing in");
        ll.addView(Radio);
        RadioButton R = new RadioButton(this);
        R.toggle();
        ll.addView(R);

        //department
        RadioGroup dep = new RadioGroup(this);
        String [] departments ={"Admin","Recreation","SNP","Maintenance","Maintenance: Rental Event Contact",
                "Maintenance: Rental Event Contact","Maintenance: RENTAL SET-UP","Maintenance: RENTAL CLEAN-UP","NCRPD Event" };
        for (int i =0;i<8;i++){
            RadioButton dep_sel = new RadioButton(this);
            dep_sel.setText(departments[i]);
            dep.addView(dep_sel);
        }
        ll.addView(dep);
    }

}