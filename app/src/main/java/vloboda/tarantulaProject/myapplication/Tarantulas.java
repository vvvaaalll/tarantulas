package vloboda.tarantulaProject.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class Tarantulas extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore fDatabase;
    FirebaseAuth fAuth;
    TarantulaAdapter myAdapter;
    ArrayList<Tarantula> tarantulaArrayList;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarantulas);

        recyclerView = findViewById(R.id.tarantulasList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fDatabase = FirebaseFirestore.getInstance();
        fAuth     = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid().toString();

        tarantulaArrayList = new ArrayList<Tarantula>();
        myAdapter = new TarantulaAdapter(Tarantulas.this, tarantulaArrayList);
        recyclerView.setAdapter(myAdapter);

        EventChangeListener();

    }

    private void EventChangeListener()
     {

         fDatabase.collection("users").document(userID).collection("tarantulas")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firestore error",error.getMessage());
                        }

                        for(DocumentChange fDatabase : value.getDocumentChanges()){

                            if(fDatabase.getType() == DocumentChange.Type.ADDED){
                              Tarantula tarantula =  fDatabase.getDocument().toObject(Tarantula.class);

                                    tarantula.setTarantulaID(fDatabase.getDocument().getId());
                                    tarantulaArrayList.add(tarantula);

                            }
                            myAdapter.notifyDataSetChanged();

                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.tarantulasMenu:
                startActivity(new Intent(getApplicationContext(), Tarantulas.class));
                return true;
            case R.id.logOutMenu:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
                return true;
            case R.id.addMenu:
                startActivity(new Intent(getApplicationContext(),AddTarantula.class));
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }





}