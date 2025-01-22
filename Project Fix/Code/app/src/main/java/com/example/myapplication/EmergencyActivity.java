package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EmergencyActivity extends AppCompatActivity {

    Button back, fire, police, ambulance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emergency);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.emergency), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.button2);
        fire = findViewById(R.id.callFireDept);
        police = findViewById(R.id.callPolice);
        ambulance = findViewById(R.id.callAmbulance);


        back.setOnClickListener(view -> finish());

        View.OnClickListener shareClickListener = (v -> finish());

        fire.setOnClickListener(shareClickListener);
        police.setOnClickListener(shareClickListener);
        ambulance.setOnClickListener(shareClickListener);





    }


}
