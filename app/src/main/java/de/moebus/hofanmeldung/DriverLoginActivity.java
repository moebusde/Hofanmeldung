package de.moebus.hofanmeldung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DriverLoginActivity extends AppCompatActivity {

    private EditText etFristname, etLastName, etBirthday, etMobilenumber;
    private Button btnNext;
    private TextView tVStatus;
    private Spinner spPrefix;

    private ArrayList<String> userdata = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);


        etFristname = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etBirthday = findViewById(R.id.etBirthday);
        etMobilenumber = findViewById(R.id.etMobilenumber);
        tVStatus = findViewById(R.id.tVStatus);
        btnNext = findViewById(R.id.btnNext);
        spPrefix = findViewById(R.id.spPrefix);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidForm()) {
                    userdata.clear();
                    userdata.add(etFristname.getText().toString().trim());
                    userdata.add(etLastName.getText().toString().trim());
                    userdata.add(etBirthday.getText().toString().trim());
                    userdata.add(spPrefix.getSelectedItem().toString().trim()+etMobilenumber.getText().toString().trim());

                    Intent intent = new Intent(getApplicationContext(), SaveDriverDataActivity.class);
                    intent.putStringArrayListExtra("userdata", userdata);

                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), R.string.fehlende_eingaben, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isValidForm() {
        if(!validateInput(etFristname) || !validateInput(etLastName) || !validateInput(etBirthday) || !validateInput(etMobilenumber)) {
            return false;
        }
        return true;
    }

    private boolean validateInput(EditText input) {
        if (input.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}