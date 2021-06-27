package vloboda.tarantulaProject.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddTarantula extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.tarantulasMenu:
                startActivity(new Intent(getApplicationContext(), Tarantulas.class));
                return true;
            case R.id.feedersMenu:
                startActivity(new Intent(getApplicationContext(), FeederInsects.class));
                return true;
            case R.id.enclosuresMenu:
                startActivity(new Intent(getApplicationContext(), Enclosures.class));
                return true;
            case R.id.logOutMenu:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    ImageView mImage;
    EditText mSpecies, mName, mOrigin;
    RadioGroup temperGroup;
    RadioGroup venomGroup;
    RadioButton temperButton;
    RadioButton venomButton;
    CheckBox mHairs;
    Button mSubmit;
    ProgressBar mProgressBar;

    String imgUrl, imgURL;

    FirebaseFirestore fStore;
    StorageReference fStorage;
    String userID;
    FirebaseAuth fAuth;
    int SELECT_PHOTO = 1;
    Uri imgUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarantula);

        mImage   = (ImageView) findViewById(R.id.imageView);
        mSpecies   = findViewById(R.id.tarantulaSpecies);
        mName   = findViewById(R.id.tarantulaName);
        mOrigin   = findViewById(R.id.tarantulaOrigin);
        temperGroup = findViewById(R.id.temperGroup);
        venomGroup = findViewById(R.id.venomGroup);
        mSubmit = findViewById(R.id.addTarantulaBtn);
        mHairs = findViewById(R.id.urticatingCheckBox);
        mProgressBar = findViewById(R.id.progressBar);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        fStorage = FirebaseStorage.getInstance().getReference("tarantulas");
        userID = fAuth.getCurrentUser().getUid();



        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name = mName.getText().toString().trim();
                String species = mSpecies.getText().toString().trim();
                String origin = mOrigin.getText().toString().trim();
                int hairs;



                mProgressBar.setVisibility(View.GONE);

                int temperID = temperGroup.getCheckedRadioButtonId();
                temperButton = findViewById(temperID);
                int temper = Integer.parseInt(temperButton.getText().toString());



                int venomID = venomGroup.getCheckedRadioButtonId();
                venomButton = findViewById(venomID);
                int venom = Integer.parseInt(venomButton.getText().toString());



                if(mHairs.isChecked()){
                     hairs = 1;
                }else { hairs = 0;}

                mProgressBar.setVisibility(View.VISIBLE);

                UploadFile();

                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("tarantulas").document();
                HashMap<String,Object> tarantula = new HashMap<>();
                tarantula.put("name",name);
                tarantula.put("species",species);
                tarantula.put("origin",origin);
                tarantula.put("temper",temper);
                tarantula.put("venom",venom);
                tarantula.put("hairs",hairs);
                tarantula.put("owner",userID.toString());
                tarantula.put("imgURL",imgURL);


                documentReference.set(tarantula).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "onSuccess: tarantula profile is created for" + userID);

                        Toast.makeText(AddTarantula.this, "Succesfully uploaded!",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "onFailure: "+e.toString());

                    }
                });



            }
        });


       mImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_PICK);
               intent.setType("image/*");
               startActivityForResult(intent,SELECT_PHOTO);
           }
       });


    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, requestCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
                mImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void UploadFile(){
        if(imgUri != null){

            StorageReference fileReference = fStorage.child(System.currentTimeMillis() + "." + getFileExtension(imgUri));
            final UploadTask uploadTask = fileReference.putFile(imgUri);


            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddTarantula.this, "Error:" + e.toString(), Toast.LENGTH_SHORT).show();

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(0);
                        }
                    }, 500);

                    Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if(!task.isSuccessful()){
                                throw task.getException();
                            }

                            //imgUrl = fileReference.getDownloadUrl().toString();
                            imgUrl = fileReference.getDownloadUrl().toString();
                            return fileReference.getDownloadUrl();

                        }
                       /* Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setProgress(0);
                            }
                        }, 500);*/
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    mProgressBar.setProgress((int) progress);
                    if(progress == 100){
                        startActivity(new Intent(getApplicationContext(), Tarantulas.class));
                    }
                }
            });









                 /*   uploadTask.(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);


                        })
                    */




        }else{
                Toast.makeText(this, "no image selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Tarantulas.class));
            }


    }


}







