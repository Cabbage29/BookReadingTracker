package com.example.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button btnGoToBooks;
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnGoToBooks = findViewById(R.id.btnGoToBooks);
        tvWelcome    = findViewById(R.id.tvWelcome);

        String username = getIntent().getStringExtra("username");
        if (username != null) {
            tvWelcome.setText("Welcome Back, " + username + "!");
        }

        btnGoToBooks.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookCategoryActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }
}
