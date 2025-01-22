package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LocationActivity extends AppCompatActivity {

    Button back, share1, share2, share3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.location), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.button3);
        share1 = findViewById(R.id.share_button1);
        share2 = findViewById(R.id.share_button2);
        share3 = findViewById(R.id.share_button3);


        back.setOnClickListener(view -> finish());

        // Set up click listeners for share buttons
        View.OnClickListener shareClickListener = v -> showEmailInputDialog();

        share1.setOnClickListener(shareClickListener);
        share2.setOnClickListener(shareClickListener);
        share3.setOnClickListener(shareClickListener);
    }

    private void showEmailInputDialog() {
        // Inflate custom layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.activity_email, null);
        EditText emailInput = dialogView.findViewById(R.id.email_input);

        // Build the dialog
        new AlertDialog.Builder(this)
                .setTitle("Share Location")
                .setView(dialogView)
                .setPositiveButton("Send", (dialog, which) -> {
                    String email = emailInput.getText().toString().trim();
                    if (!email.isEmpty()) {
                        sendEmail(email);
                    } else {
                        Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void sendEmail(String email) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Secure Kelowna Location Alert");
        intent.putExtra(Intent.EXTRA_TEXT, "A user is sending you their location right now. They are currently located at EXAMPLE");
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose email client"));
    }




    }



