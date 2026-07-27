package com.example.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class BookCategoryActivity extends AppCompatActivity {

    Button btnBack, btnFiction, btnNonFiction, btnComics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_category);

        btnBack       = findViewById(R.id.btnBack);
        btnFiction    = findViewById(R.id.btnFiction);
        btnNonFiction = findViewById(R.id.btnNonFiction);
        btnComics     = findViewById(R.id.btnComics);

        btnBack.setOnClickListener(v -> finish());

        btnFiction.setOnClickListener(v -> openBookList("Fiction"));
        btnNonFiction.setOnClickListener(v -> openBookList("Non-Fiction"));
        btnComics.setOnClickListener(v -> openBookList("Comics and Manga"));
    }

    private void openBookList(String category) {
        Intent intent = new Intent(this, BookListActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
