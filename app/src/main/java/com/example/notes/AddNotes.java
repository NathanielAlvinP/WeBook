package com.example.notes;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;

public class AddNotes extends AppCompatActivity {
    private ImageView save;
    private EditText title;
    private EditText desc;
    private ImageView addImage;
    private ImageView imageContainer;
    private static final String TAG = "AddNotes";
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private Note note = new Note();
    private StorageReference mStorageRef;
    private TextView timestamp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnotes);
        save = findViewById(R.id.simpanNotes);
        title = findViewById(R.id.titleNote);
        desc = findViewById(R.id.descriptionNote);
        addImage = findViewById(R.id.addImage);
        imageContainer = findViewById(R.id.chosenImage);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        timestamp = findViewById(R.id.lastEdit);

        String currentDate = DateFormat.getDateInstance().format(new Date());

        timestamp.setText(currentDate);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
                addNote(title.getText().toString(), desc.getText().toString());
                Intent intent = new Intent(AddNotes.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        addImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
    }
    private void addNote(String title, String desc) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        note.setCompletedTask(false);
        note.setJudul(title);
        note.setIsi(desc);
        note.setCreatedDate(new Timestamp(new Date()));
        note.setUserId(userId);

        FirebaseFirestore.getInstance()
                .collection("Notes")
                .add(note)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "onSuccess: Succesfully added the note...");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNotes.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(imageContainer);
        }
    }
    //get extension for all file
    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private void uploadFile(){
        if(mImageUri != null){
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
            Toast.makeText(this, mImageUri.toString(), Toast.LENGTH_SHORT).show();
            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    uploadImg img = new uploadImg(fileReference.getDownloadUrl().toString());
                    Toast.makeText(AddNotes.this,taskSnapshot.getStorage().getDownloadUrl().toString() , Toast.LENGTH_SHORT).show();
                    note.setImage(img.getImageUrl());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddNotes.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
//            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//
//                }
//            });
        }
    }
}
