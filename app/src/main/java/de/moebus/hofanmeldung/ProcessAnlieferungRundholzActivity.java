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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProcessAnlieferungRundholzActivity extends AppCompatActivity {

    private EditText etWorknumber, etPartienumber, etCar, etNumberLogs;
    private Spinner spCrane, spWoodType, spWoodLength;
    private Button btnNext, btnScanCode;

    private Intent intent, data;

    private int sNumberLogs;
    private String sPartienumber, sWoodType, sWorknumber, sCar;
    private float sWoodLength;
    private boolean isScanned = false, sCrane;
    private ArrayList<String> userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_anlieferung_rundholz);

        IntroActivity.userDatabase.userDAO().deleteRundholzAssignment(1);

        etWorknumber = findViewById(R.id.etWorknumber);
        etPartienumber = findViewById(R.id.etPartienumber);
        etCar = findViewById(R.id.etCar);

        etNumberLogs = findViewById(R.id.etNumberLogs);
        spCrane = findViewById(R.id.spCrane);
        spWoodType = findViewById(R.id.spWoodType);
        spWoodLength = findViewById(R.id.spWoodLength);
        btnNext = findViewById(R.id.btnNext);
        btnScanCode = findViewById(R.id.btnScanCode);


        if (!IntroActivity.isDatabaseUsed) {
            data = getIntent();
            userdata = data.getStringArrayListExtra("userdata");
        }

        intent = new Intent(getApplicationContext(), SummaryActivity.class);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isValidForm()) {
//                    SQLConnector sqlConnector = new SQLConnector();

                    sWorknumber = etWorknumber.getText().toString().trim();
                    sPartienumber = etPartienumber.getText().toString().trim();
                    sCar = etCar.getText().toString().trim();
                    sNumberLogs = Integer.parseInt(etNumberLogs.getText().toString().trim());
                    sWoodType = spWoodType.getSelectedItem().toString().trim();
                    sWoodLength = Float.parseFloat(spWoodLength.getSelectedItem().toString().trim());

                    if(spCrane.getSelectedItemPosition() == 0) {
                        sCrane = true;
                    } else {
                        sCrane = false;
                    }

//                    try {
//                        if (sqlConnector.arbeitsauftragsnummerMatchesPartienummer(sPartienumber, sWorknumber)) {
                            Rundholz rundholz = new Rundholz(1, sWorknumber, sPartienumber, sCar, sCrane, sWoodType, sWoodLength, sNumberLogs);
                            if(IntroActivity.userDatabase.userDAO().getRundholzAssignment() != null) {
                                IntroActivity.userDatabase.userDAO().updateRundholzAssignment(rundholz.getWorknumber(), rundholz.getPartienumber(), rundholz.getCar(), rundholz.isCrane(), rundholz.getWoodType(), Float.parseFloat(rundholz.getWoodType()), rundholz.getNumberLogs());
                            } else {
                                IntroActivity.userDatabase.userDAO().addRundholzAssignment(rundholz);
                            }
                            //Rundholz rundholz = new Rundholz(1, sWorknumber, sPartienumber, sCar, sCrane, sWoodType, sWoodLength, sNumberLogs);
                            startActivity(intent);
//                        } else {
//                            Toast.makeText(getApplicationContext(), R.string.auftragsnummer_partienummer_keine_uebereinstimmung, Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (SQLException e) {
//                        Toast.makeText(getApplicationContext(), R.string.interner_fehler, Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.fehlende_eingaben, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt(getString(R.string.scan_hinweis));
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
//                SQLConnector sqlConnector = new SQLConnector();
//                try {
//                    String partienumber = sqlConnector.getPartienummer(result.getContents());
                    etWorknumber.setText(result.getContents());
//                    etPartienumber.setText(partienumber);
//                    etPartienumber.setEnabled(false);
                    isScanned = true;
//                } catch (SQLException e) {
//                    Toast.makeText(this, R.string.scan_barcode_ungueltig, Toast.LENGTH_LONG).show();
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
            } else {
                Toast.makeText(this, R.string.rundholz_scan_abbruch, Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private boolean isValidForm() {
        if(!validateInput(etWorknumber)) {
            Toast.makeText(this, "Arbeitsauftragsnummer", Toast.LENGTH_SHORT).show();
            if(!validateInput(etPartienumber)) {
                Toast.makeText(this, "Partienummer", Toast.LENGTH_SHORT).show();
                if(!validateSpinner(spCrane)) {
                    Toast.makeText(this, "LKW", Toast.LENGTH_SHORT).show();
                    if(!validateSpinner(spWoodType)) {
                        Toast.makeText(this, "Holzart", Toast.LENGTH_SHORT).show();
                        if(validateSpinner(spWoodLength)) {
                            Toast.makeText(this, "Holzlänge", Toast.LENGTH_SHORT).show();
                            if(!validateInput(etNumberLogs)) {
                                Toast.makeText(this, "Stämme", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
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

    private boolean validateSpinner(Spinner spinner) {
        if (spinner.getSelectedItem().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}