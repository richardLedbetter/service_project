package com.example.service_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.service_project.Model.Park;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //import firebase
    private DatabaseReference mDatabase;
    //list of parks
    List<Park> parks;


    LinearLayout ll;
    int ScreenHeight,ScreenWidth;
    DisplayMetrics displayMetrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = findViewById(R.id.ll);
        displayMetrics = this.getResources().getDisplayMetrics();
        ScreenWidth = (int) (displayMetrics.widthPixels);
        ScreenHeight = (int) (displayMetrics.heightPixels );

        set_up();
    }

    //used to add new parks
    private void writeNewPark(String name, String location) {
        Park parck1 = new Park(name, location);

        //send park object to database
        mDatabase.child("parks").child(name).setValue(parck1);
    }
    public List<Park> getparks(){

        mDatabase =FirebaseDatabase.getInstance().getReference().child("parks");
        parks = new ArrayList<>();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot tempSnapshot : dataSnapshot.getChildren()){

                    String name = Objects.requireNonNull(tempSnapshot.child("name").getValue()).toString();
                    String location = tempSnapshot.child("location").getValue().toString();

                    parks.add(new Park (name,location));
                    System.out.println("info i am looking for "+parks.size());
                }
                // once all data is retrived go to set up

                set_up();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error Cancelled");
            }
        });
        return parks;

    }


    @SuppressLint("SetTextI18n")

    public void set_up(){
        Button b2 = new Button(this);
        b2.setText("time card");
        b2.setHeight(ScreenHeight/5);
        b2.setOnClickListener(v1->{
            Intent next_page = new Intent(this,time_card.class);
            startActivity(next_page);
        });
        ll.addView(b2);
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
