package vloboda.tarantulaProject.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class Enclosures extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enclosures);


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


}