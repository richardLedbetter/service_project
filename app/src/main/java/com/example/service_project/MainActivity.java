package com.example.service_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.service_project.Model.Park;
import com.example.service_project.Model.TimeCard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class MainActivity extends AppCompatActivity {

    //import firebase

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("parks_rec");


    // Create a storage reference from our app
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    //list of parks
    List<Park> parks;


    LinearLayout ll;
    int ScreenHeight,ScreenWidth;
    DisplayMetrics displayMetrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = findViewById(R.id.ll);
        displayMetrics = this.getResources().getDisplayMetrics();
        ScreenWidth = (int) (displayMetrics.widthPixels);
        ScreenHeight = (int) (displayMetrics.heightPixels );
        writeNewPark("rod","loc");

        set_up();
    }

    //used to add new parks
    private void writeNewPark(String name, String location) {
        //send park object to database
        DatabaseReference parkRef = ref.child("parks");
        DatabaseReference newPostRef = parkRef.push();
        newPostRef.setValue(new Park(name, location));

    }
    private void writeNewTimeCard(String employecode,String singinstatus , String department, String lunchbreak){
        // send TimeCard obj to database
        DatabaseReference timecadRef = ref.child("timecard");
        DatabaseReference newtimecardRef = timecadRef.push();
        newtimecardRef.setValue(new TimeCard(employecode,singinstatus,department,lunchbreak));


    }
    private void wrireNewPic(String path){
        Uri file = Uri.fromFile(new File(path));
        StorageReference riversRef = storageRef.child("images/"+file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }

    public List<Park> getparks(){

        ref =FirebaseDatabase.getInstance().getReference().child("parks");
        parks = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
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
