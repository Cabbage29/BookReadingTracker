package com.example.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BookDetailActivity extends AppCompatActivity {

    Button btnBack, btnAddToLog, btnViewSummary;
    TextView tvBookTitle, tvBookAuthor, tvDifficulty, tvReadingTime, tvDescription;

    // Shared reading log across the app
    static ArrayList<String[]> readingLog = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        btnBack        = findViewById(R.id.btnBack);
        btnAddToLog    = findViewById(R.id.btnAddToLog);
        btnViewSummary = findViewById(R.id.btnViewSummary);
        tvBookTitle    = findViewById(R.id.tvBookTitle);
        tvBookAuthor   = findViewById(R.id.tvBookAuthor);
        tvDifficulty   = findViewById(R.id.tvDifficulty);
        tvReadingTime  = findViewById(R.id.tvReadingTime);
        tvDescription  = findViewById(R.id.tvDescription);

        String title       = getIntent().getStringExtra("title");
        String author      = getIntent().getStringExtra("author");
        String difficulty  = getIntent().getStringExtra("difficulty");
        String readingTime = getIntent().getStringExtra("readingTime");
        String description = getIntent().getStringExtra("description");

        tvBookTitle.setText(title);
        tvBookAuthor.setText("Author: " + author);
        tvDifficulty.setText("Difficulty: " + difficulty);
        tvReadingTime.setText("Estimated Reading Time: " + readingTime);
        tvDescription.setText(description);

        btnBack.setOnClickListener(v -> finish());

        btnAddToLog.setOnClickListener(v -> {
            readingLog.add(new String[]{title, author, difficulty, readingTime});
            Toast.makeText(this, title + " added to reading log!", Toast.LENGTH_SHORT).show();
        });

        btnViewSummary.setOnClickListener(v -> {
            startActivity(new Intent(this, SummaryActivity.class));
        });
    }
}
