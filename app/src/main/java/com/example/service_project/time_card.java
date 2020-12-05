package com.example.service_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.service_project.Model.Employe;

public class
time_card extends AppCompatActivity {
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
        Radio.setGravity(Gravity.CENTER);
        Radio.setHeight(150);
        Radio.setText("Signing in");
        ll.addView(Radio);
        RadioButton R = new RadioButton(this);
        R.toggle();
        ll.addView(R);

        //department
        TextView Deparment_text = new TextView(this);
        Deparment_text.setGravity(Gravity.CENTER);
        Deparment_text.setHeight(150);
        Deparment_text.setText("Deparment");
        ll.addView(Deparment_text);
        RadioGroup dep = new RadioGroup(this);
        String [] departments ={"Admin","Recreation","SNP","Maintenance","Maintenance: Rental Event Contact",
                "Maintenance: Rental Event Contact","Maintenance: RENTAL SET-UP","Maintenance: RENTAL CLEAN-UP","NCRPD Event" };
        for (int i =0;i<8;i++){
            RadioButton dep_sel = new RadioButton(this);
            dep_sel.setText(departments[i]);
            dep.addView(dep_sel);
        }
        ll.addView(dep);

        //Lunch break
        TextView Lunch_break = new TextView(this);
        Lunch_break.setText("LUNCH BREAK");
        Lunch_break.setGravity(Gravity.CENTER);
        Lunch_break.setHeight(150);
        ll.addView(Lunch_break);
        RadioGroup Lunch = new RadioGroup(this);
        String [] options = {"Lunch Break","I did NOT take my Lunch today (Agreed by Employee and Manager)"
        ,"I did NOT take my lunch today (Not Authorized).","NOT APPLICABLE"};
        for (int i = 0;i<options.length;i++){
            RadioButton tmp = new RadioButton(this);
            tmp.setText(options[i]);
            Lunch.addView(tmp);
        }
        ll.addView(Lunch);

    }

}