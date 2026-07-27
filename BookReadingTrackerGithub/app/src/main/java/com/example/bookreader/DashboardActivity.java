package com.example.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    Button btnGoToBooks, btnLogout;
    TextView tvWelcome;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth        = FirebaseAuth.getInstance();
        btnGoToBooks = findViewById(R.id.btnGoToBooks);
        btnLogout    = findViewById(R.id.btnLogout);
        tvWelcome    = findViewById(R.id.tvWelcome);

        String username = getIntent().getStringExtra("username");
        if (username != null) {
            tvWelcome.setText("Welcome, " + username + "!");
        }

        btnGoToBooks.setOnClickListener(v -> {
            startActivity(new Intent(this, BookCategoryActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
