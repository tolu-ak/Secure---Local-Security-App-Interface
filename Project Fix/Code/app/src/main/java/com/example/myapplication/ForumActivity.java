package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ForumActivity extends AppCompatActivity {

    private Button upvoteB1, upvoteB2, home, alert;
    private ImageView upvote1, upvote2, downvote1, downvote2;
    private TextView counter1, counter2;

    private SearchView searchView;
    private List<String> forumData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web);


        initializeViews();


        handleIntentExtras();

        setupSearchView();


        setupClickListeners();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeViews() {
        // Forum data
        forumData = new ArrayList<>();
        forumData.add("Incident report 1 by User1");
        forumData.add("Incident report 2 by User2");
        forumData.add("General forum post by User3");

        searchView = findViewById(R.id.searchView);

        counter1 = findViewById(R.id.count1);
        upvote1 = findViewById(R.id.upvoteArrow1);
        upvoteB1 = findViewById(R.id.button_upvote1);
        counter2 = findViewById(R.id.count2);
        upvote2 = findViewById(R.id.upvoteArrow2);
        upvoteB2 = findViewById(R.id.button_upvote2);

        downvote1 = findViewById(R.id.downvoteArrow1);
        downvote2 = findViewById(R.id.downvoteArrow2);

        home = findViewById(R.id.button_home);
        alert = findViewById(R.id.button_alert);
    }

    private void handleIntentExtras() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }

        TextView type = findViewById(R.id.alertType5);
        TextView name = findViewById(R.id.textName5);
        TextView desc = findViewById(R.id.desc5);
        TextView time = findViewById(R.id.time6);
        Button comment = findViewById(R.id.button_comment5);
        Button upvote = findViewById(R.id.button_upvote5);
        ImageView upArrow = findViewById(R.id.upvoteArrow5);
        ImageView downArrow = findViewById(R.id.downvoteArrow5);
        TextView count = findViewById(R.id.count6);
        ImageView profilePic = findViewById(R.id.pfp5);

        boolean visibility = bundle.getBoolean("visibility", false);
        String nameS = bundle.getString("name", "Unknown");
        String typeS = bundle.getString("type", "Unknown");
        String descS = bundle.getString("description", "No description available");
        String timeS = bundle.getString("time", "");
        String dateS = bundle.getString("date", "");
        String locationS = bundle.getString("location", "Unknown location");

        if (visibility) {
            type.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            desc.setVisibility(View.VISIBLE);
            comment.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);
            upvote.setVisibility(View.VISIBLE);
            upArrow.setVisibility(View.VISIBLE);
            downArrow.setVisibility(View.VISIBLE);
            count.setVisibility(View.VISIBLE);
            profilePic.setVisibility(View.VISIBLE);
        }

        type.setText(typeS);
        name.setText(nameS);
        desc.setText(descS);
        time.setText(dateS + ", " + timeS);
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });
    }

    private void performSearch(String query) {
        List<String> filteredList = new ArrayList<>();
        for (String item : forumData) {
            if (item.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (!filteredList.isEmpty()) {
            Toast.makeText(this, "Results: " + filteredList, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupClickListeners() {
        setupVoteClickListeners(counter1, upvoteB1, upvote1, downvote1);
        setupVoteClickListeners(counter2, upvoteB2, upvote2, downvote2);

        home.setOnClickListener(view -> finish());

        alert.setOnClickListener(view -> {
            Intent intent = new Intent(ForumActivity.this, AlertActivity.class);
            startActivity(intent);
        });
    }

    private void setupVoteClickListeners(TextView counter, Button upvoteButton, ImageView upvoteImage, ImageView downvoteImage) {
        View.OnClickListener upvoteListener = view -> updateCounter(counter, 1);
        upvoteButton.setOnClickListener(upvoteListener);
        upvoteImage.setOnClickListener(upvoteListener);

        downvoteImage.setOnClickListener(view -> updateCounter(counter, -1));
    }

    private void updateCounter(TextView counter, int delta) {
        try {
            int currentCount = Integer.parseInt(counter.getText().toString());
            counter.setText(String.valueOf(currentCount + delta));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid counter value", Toast.LENGTH_SHORT).show();
        }
    }
}


