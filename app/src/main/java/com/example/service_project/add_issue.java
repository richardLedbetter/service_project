package com.example.service_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class add_issue extends AppCompatActivity {

    LinearLayout ll;
    ImageView imageView;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_issue);
        ll = findViewById(R.id.add_issue_scroller);
        imageView = new ImageView(this);
        pop();
    }
    public void upload (){
        //needs code for upload
        //datatypes
        //string , photo
    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
    public void pop(){
        Button add = new Button(this);
        add.setWidth(300);
        add.setOnClickListener(v1->{
            Runnable task2 = new Runnable() {
                @Override
                public void run() {
                    upload();
                }
            };
            Thread thread2 = new Thread(task2);
        });

        EditText discription = new EditText(this);

        Button photo = new Button(this);
        photo.setWidth(300);
        photo.setOnClickListener(v1->{

            openGallery();
        });

        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                ll.addView(photo);
                ll.addView(discription);
                ll.addView(add);


            }
        };
        runOnUiThread(task1);
    }
}