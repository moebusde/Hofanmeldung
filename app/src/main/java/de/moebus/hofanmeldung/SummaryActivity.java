package de.moebus.hofanmeldung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private TextView tvSummaryFirstName, tvSummaryLastName, tvSummaryBirthday, tvSummaryMobilenumber, tvSummaryProcess, tvSummaryWorknumber, tvSummaryPartienumber, tvSummaryCar,
            tvSummaryCrane, tvSummaryWoodType, tvSummaryWoodLength, tvSummaryNumberLogs;

    private Intent completedata;
    private User user;
    private Rundholz rundholz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        //Personal data
        tvSummaryFirstName = findViewById(R.id.tvSummaryFirstName);
        tvSummaryLastName = findViewById(R.id.tvSummaryLastName);
        tvSummaryBirthday = findViewById(R.id.tvSummaryBirthday);
        tvSummaryMobilenumber = findViewById(R.id.tvSummaryMobilenumber);

        //Work data
        tvSummaryProcess = findViewById(R.id.tvSummaryProcess);
        tvSummaryWorknumber = findViewById(R.id.tvSummaryWorknumber);
        tvSummaryPartienumber = findViewById(R.id.tvSummaryPartienumber);
        tvSummaryCar = findViewById(R.id.tvSummaryCar);
        tvSummaryCrane = findViewById(R.id.tvSummaryCrane);
        tvSummaryWoodType = findViewById(R.id.tvSummaryWoodType);
        tvSummaryWoodLength = findViewById(R.id.tvSummaryWoodLength);
        tvSummaryNumberLogs = findViewById(R.id.tvSummaryNumberLogs);

        user = IntroActivity.userDatabase.userDAO().getUser();
        rundholz = IntroActivity.userDatabase.userDAO().getRundholzAssignment();

        //Personal data
        tvSummaryFirstName.setText(user.getFirstName());
        tvSummaryLastName.setText(user.getName());
        tvSummaryBirthday.setText(user.getBirthday());
        tvSummaryMobilenumber.setText(user.getMobile());

        //Work data
        tvSummaryWorknumber.setText(rundholz.getWorknumber());
        tvSummaryPartienumber.setText(rundholz.getPartienumber());
        tvSummaryCar.setText(rundholz.getCar());
        if(rundholz.isCrane()) {
            tvSummaryCrane.setText(R.string.saveDriverData_button_Ja);
        } else {
            tvSummaryCrane.setText(R.string.saveDriverData_button_Nein);
        }
        tvSummaryWoodType.setText(rundholz.getWoodType());
        tvSummaryWoodLength.setText(Float.toString(rundholz.getWoodLength()));
        tvSummaryNumberLogs.setText(Integer.toString(rundholz.getNumberLogs()));
    }
}