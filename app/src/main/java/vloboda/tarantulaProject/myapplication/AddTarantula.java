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
import com.google.firebase.firestore.CollectionReference;
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
    RadioButton temperButton1;
    RadioButton temperButton2;
    RadioButton temperButton3;
    RadioButton temperButton4;
    RadioButton temperButton5;
    RadioButton temperButton6;

    RadioButton venomButton1;
    RadioButton venomButton2;
    RadioButton venomButton3;
    CheckBox mHairs;
    Button mSubmit;
    ProgressBar mProgressBar;

    String imgUrl, imgName;

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

        temperButton1 = findViewById(R.id.temper1);
        temperButton2 = findViewById(R.id.temper2);
        temperButton3 = findViewById(R.id.temper3);
        temperButton4 = findViewById(R.id.temper4);
        temperButton5 = findViewById(R.id.temper5);
        temperButton6 = findViewById(R.id.temper6);

        venomButton1 = findViewById(R.id.venom1);
        venomButton2 = findViewById(R.id.venom2);
        venomButton3 = findViewById(R.id.venom3);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        fStorage = FirebaseStorage.getInstance().getReference();

        userID = fAuth.getCurrentUser().getUid();

        mProgressBar.setVisibility(View.GONE);




        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name = mName.getText().toString().trim();
                String species = mSpecies.getText().toString().trim();
                String origin = mOrigin.getText().toString().trim();
                int hairs;
                int temper = 0;
                int venom = 0;




                if(temperButton1.isChecked()){
                     temper = Integer.parseInt(temperButton1.getText().toString());
                }else if(temperButton2.isChecked()){
                     temper = Integer.parseInt(temperButton2.getText().toString());
                }else if(temperButton3.isChecked()){
                     temper = Integer.parseInt(temperButton3.getText().toString());
                }else if(temperButton4.isChecked()){
                     temper = Integer.parseInt(temperButton4.getText().toString());
                }else if(temperButton5.isChecked()){
                     temper = Integer.parseInt(temperButton5.getText().toString());
                } else if(temperButton6.isChecked()){
                     temper = Integer.parseInt(temperButton6.getText().toString());
                }


                if(venomButton1.isChecked()){
                    venom = Integer.parseInt(venomButton1.getText().toString());
                }else if(venomButton2.isChecked()){
                    venom = Integer.parseInt(venomButton2.getText().toString());
                } else if(venomButton3.isChecked()){
                    venom = Integer.parseInt(venomButton3.getText().toString());
                }


                if(mHairs.isChecked()){
                     hairs = 1;
                }else { hairs = 0;}

                mProgressBar.setVisibility(View.VISIBLE);

                UploadFile();


                DocumentReference documentReference = fStore.collection("users").document(userID).collection("tarantulas").document();

                HashMap<String,Object> tarantula = new HashMap<>();
                tarantula.put("name",name);
                tarantula.put("species",species);
                tarantula.put("origin",origin);
                tarantula.put("temper",temper);
                tarantula.put("venom",venom);
                tarantula.put("hairs",hairs);
                tarantula.put("imgName", imgName);


                documentReference.set(tarantula).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "onSuccess: tarantula profile is created for" + userID);

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
            imgName = (System.currentTimeMillis() + "." + getFileExtension(imgUri));
            StorageReference fileReference = fStorage.child("tarantulas").child(imgName);
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

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    mProgressBar.setProgress((int) progress);
                    if(progress == 100){
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(AddTarantula.this, "Succesfully uploaded!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Tarantulas.class));
                    }
                }
            });


        }else{
                //Toast.makeText(this, "no image selected", Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(AddTarantula.this, "Succesfully uploaded!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Tarantulas.class));
            }


    }


}







