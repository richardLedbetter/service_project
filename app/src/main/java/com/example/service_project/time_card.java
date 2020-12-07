package com.example.service_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.service_project.Model.Employe;
import com.example.service_project.Model.TimeCard;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class
time_card extends AppCompatActivity {
    LinearLayout ll;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_card);
        ll = findViewById(R.id.form);
        pop();

    }
    private void upload(String timeStamp, String employecode,String singinstatus , String department, String lunchbreak){
        // send TimeCard obj to database
        TimeCard timeCard = new TimeCard(employecode,singinstatus,department,lunchbreak);
        mDatabase.child("timecards").child(employecode+"-"+timeStamp).setValue(timeCard);

    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("MM-dd-yyyy-hh:mm", cal).toString();


        return date;
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
        //R.toggle();
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
        for (int i =0;i<departments.length;i++){
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
        Button Submit = new Button(this);
        Submit.setHeight(200);
        Submit.setText("SUBMIT");
        Submit.setOnClickListener(v1->{

            String format = getDate(System.currentTimeMillis()/1000);
            Log.d("pop:", format);
            upload(format
                    ,ID.getText().toString(),Boolean.toString(R.isChecked()),
                    departments[dep.getCheckedRadioButtonId()],
                    options[Lunch.getCheckedRadioButtonId()-departments.length]);
        });
        ll.addView(Submit);

    }

}