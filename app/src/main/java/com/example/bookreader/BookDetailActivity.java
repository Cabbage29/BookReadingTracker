package com.example.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookDetailActivity extends AppCompatActivity {

    Button btnBack, btnAddToLog, btnViewSummary;
    TextView tvBookTitle, tvBookAuthor, tvDifficulty, tvReadingTime, tvDescription;

    // Firebase
    FirebaseFirestore db;
    FirebaseAuth mAuth;

    // Local reading log for summary screen
    static ArrayList<String[]> readingLog = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // Initialize Firebase
        db    = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

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
            // Save to local list for summary
            readingLog.add(new String[]{title, author, difficulty, readingTime});

            // Save to Firestore database
            String userId = mAuth.getCurrentUser().getUid();
            Map<String, Object> book = new HashMap<>();
            book.put("title",       title);
            book.put("author",      author);
            book.put("difficulty",  difficulty);
            book.put("readingTime", readingTime);

            db.collection("users")
                .document(userId)
                .collection("readingLog")
                .add(book)
                .addOnSuccessListener(ref ->
                    Toast.makeText(this, title + " saved to reading log!", Toast.LENGTH_SHORT).show()
                )
                .addOnFailureListener(e ->
                    Toast.makeText(this, "Failed to save: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
        });

        btnViewSummary.setOnClickListener(v -> {
            startActivity(new Intent(this, SummaryActivity.class));
        });
    }
}
