package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity {

    private EditText etLocationRadius;
    private Spinner spinnerSeverityThreshold, spinnerHasAttachments, spinnerStatus;
    private Button btnBack, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);


        etLocationRadius = findViewById(R.id.et_location_radius);
        spinnerSeverityThreshold = findViewById(R.id.spinner_severity_threshold);
        spinnerHasAttachments = findViewById(R.id.spinner_has_attachments);
        spinnerStatus = findViewById(R.id.spinner_status);
        btnBack = findViewById(R.id.btn_back);
        btnSave = findViewById(R.id.btn_save);


        btnBack.setOnClickListener(v -> finish());


        btnSave.setOnClickListener(v -> {
            String locationRadius = etLocationRadius.getText().toString();
            String severityThreshold = spinnerSeverityThreshold.getSelectedItem().toString();
            String hasAttachments = spinnerHasAttachments.getSelectedItem().toString();
            String status = spinnerStatus.getSelectedItem().toString();


            Toast.makeText(this, "Preferences saved", Toast.LENGTH_LONG).show();

            // Optional: Finish the current activity if you don't want to return to it
            new Handler().postDelayed(this::finish, 3500);
        });



    }
}
