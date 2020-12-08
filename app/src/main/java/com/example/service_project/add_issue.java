package com.example.service_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class add_issue extends AppCompatActivity {

    LinearLayout ll;
    File tmp;
    ImageView imageView;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    FirebaseStorage fire = FirebaseStorage.getInstance();
    StorageReference storageRef = fire.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_issue);
        ll = findViewById(R.id.add_issue_scroller);
        imageView = new ImageView(this);
        pop();
    }
    private void upload2(String path){
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
    public void upload (String path){
        //needs code for upload
        //datatypes
        //string , photo
        upload2(path);
    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                //Log.d("photopath", imageUri.getPath());
                tmp = File.createTempFile("sample",".PNG");
                OutputStream outStream = new FileOutputStream(tmp);
                selectedImage.compress(Bitmap.CompressFormat.PNG,100,outStream);
                outStream.close();
                imageView.setImageBitmap(selectedImage);
                ll.addView(imageView);
            } catch (FileNotFoundException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            //Toast.makeText(PostImage.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
    public void pop(){
        Button add = new Button(this);
        add.setWidth(300);
        add.setText("SUBMIT");
        add.setOnClickListener(v1->{
            Runnable task2 = new Runnable() {
                @Override
                public void run() {
                    String TAG = "line 87";

                    Log.d(TAG, tmp.getPath());
                    upload(tmp.getPath());
                }
            };
            Thread thread2 = new Thread(task2);
            thread2.start();
        });

        EditText discription = new EditText(this);

        Button photo = new Button(this);
        photo.setWidth(300);
        photo.setText("Select photo");
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