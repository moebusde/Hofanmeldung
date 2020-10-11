package de.moebus.hofanmeldung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SaveDriverDataActivity extends AppCompatActivity {

    Button btnYes, btnNo;

    TextView tvInfo;

    ArrayList<String> userdata;
    Intent intent, data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_driver_data);



        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);

        tvInfo = findViewById(R.id.tvInfo);

        data = getIntent();

        userdata = data.getStringArrayListExtra("userdata");
        intent = new Intent(getApplicationContext(), ProcessSelectionActivity.class);


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(1, userdata.get(0), userdata.get(1), userdata.get(2), userdata.get(3));
                IntroActivity.userDatabase.userDAO().addUser(user);

                Toast.makeText(getApplicationContext(), R.string.login_toast_fahrdaten_gespeichert, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putStringArrayListExtra("userdata", userdata);
                IntroActivity.isDatabaseUsed = false;
                startActivity(intent);
            }
        });

    }
}