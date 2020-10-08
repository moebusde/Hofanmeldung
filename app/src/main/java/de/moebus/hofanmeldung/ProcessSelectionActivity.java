package de.moebus.hofanmeldung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProcessSelectionActivity extends AppCompatActivity {

    private RadioGroup rGrpProzesse;
    private Button btnWeiter;
    private TextView tVTopText, tVStatus;
    private ArrayList<String> fahrerdaten;
    private Intent intent, data;

    private String firstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processselection);


        tVTopText = findViewById(R.id.tVTopText);

        rGrpProzesse = findViewById(R.id.rGrpProzesse);
        btnWeiter = findViewById(R.id.btnNext);
        tVStatus = findViewById(R.id.tVStatus);

        if (IntroActivity.isDatabaseUsed) {
            User user = IntroActivity.userDatabase.userDAO().getUser();
            firstName = user.getFirstName();
        } else {
            data = getIntent();
            fahrerdaten = data.getStringArrayListExtra("userdata");
            firstName = fahrerdaten.get(0);
        }

        tVTopText.setText(getString(R.string.processSelection_textview_willkommen, firstName));

        btnWeiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (rGrpProzesse.getCheckedRadioButtonId()) {
                    case R.id.rBtnRundholz:
                        intent = new Intent(view.getContext(), ProcessAnlieferungRundholzActivity.class);
                    break;
                    case R.id.rBtnSchnittholz:
                        intent = new Intent(view.getContext(), ProcessAbholungSchnittholzActivity.class);
                    break;
                    case R.id.rBtnAbholungSaegenebenprodukte:
                        intent = new Intent(view.getContext(), ProcessAbholungSaegenebenprodukteActivity.class);
                    break;
                    case R.id.rBtnDienstleister:
                        intent = new Intent(view.getContext(), ProcessDienstleisterActivity.class);
                    break;
                    default:
                        Toast.makeText(getApplicationContext(),"Ein interner Fehler ist aufgetreten. Das tut uns leid!", Toast.LENGTH_LONG).show();
                    break;
                }

                startActivity(intent);
            }
        });
    }
}