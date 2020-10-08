package de.moebus.hofanmeldung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    private Button btnStart;
    private Intent intent;


    public static boolean isDatabaseUsed = true;

    public static UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "userdb").allowMainThreadQueries().build();

        btnStart = findViewById(R.id.btnStart);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userDatabase.userDAO().getUser() != null) {
                    intent = new Intent(getApplicationContext(), ProcessSelectionActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), DriverLoginActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.intro_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnDeleteUser:
                userDatabase.userDAO().deleteUser(new User(1));
                userDatabase.userDAO().deleteRundholzAssignment(new Rundholz(1));
        }
        return true;
    }
}