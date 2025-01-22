package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class AlertActivity extends AppCompatActivity {

    CharSequence text = "";
    int duration;
    Button cancel, save;
    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText nameIncident, dateIncident, timeIncident, locationIncident, descIncident, typeIncident;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alert);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.alert), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cancel = findViewById(R.id.btn_cancel);
        save = findViewById(R.id.btn_save);

        nameIncident = findViewById(R.id.et_name);
        dateIncident = findViewById(R.id.et_date);
        timeIncident = findViewById(R.id.et_time);
        locationIncident = findViewById(R.id.et_location);
        descIncident = findViewById(R.id.et_description);
        typeIncident = findViewById(R.id.et_type);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateHere = dateIncident.getText().toString();
                String name = nameIncident.getText().toString();
                String location = locationIncident.getText().toString();
                String type = typeIncident.getText().toString();
                String desc = descIncident.getText().toString();
                String time = timeIncident.getText().toString();
                

                if (time.isEmpty()) {
                    text = "Enter an approximate time of incident / discovery";
                    Toast.makeText(AlertActivity.this, text, duration).show();
                    return;

                } else if (type.isEmpty()) {
                    text = "Input a title explaining incident";
                    Toast.makeText(AlertActivity.this, text, duration).show();
                    return;
                } else if (dateHere.isEmpty()) {
                    text = "Enter date of birth";
                    Toast.makeText(AlertActivity.this, text, duration).show();
                    return;
                } else if (location.isEmpty()) {
                    text = "Input a location";
                    Toast.makeText(AlertActivity.this, text, duration).show();
                    return;
                } else if(name.isEmpty()) name = "Anonymous";

                Intent intent = new Intent(AlertActivity.this, ForumActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("type", type);
                bundle.putString("location", location);
                bundle.putString("date", dateHere);
                bundle.putString("description", desc);
                bundle.putString("time", time);
                bundle.putBoolean("visibility", true);
                intent.putExtras(bundle);
                startActivity(intent);

            }

        });



    }

    public void dateDialog(View view) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String response = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        dateIncident.setText(response);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
