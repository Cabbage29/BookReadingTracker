package com.example.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookListActivity extends AppCompatActivity {

    Button btnBack;
    TextView tvCategoryTitle;
    LinearLayout llBookList;

    // Book data: {title, author, difficulty, readingTime, description}
    String[][] fictionBooks = {
        {"The Alchemist",        "Paulo Coelho",          "Easy",   "5 hrs", "A shepherd's journey to find treasure and purpose."},
        {"Harry Potter",         "J.K. Rowling",          "Easy",   "8 hrs", "A young wizard discovers a magical world."},
        {"1984",                 "George Orwell",         "Hard",   "7 hrs", "A chilling dystopian story of surveillance and control."},
        {"To Kill a Mockingbird","Harper Lee",            "Medium", "6 hrs", "A story about justice and morality in the deep South."},
        {"The Great Gatsby",     "F. Scott Fitzgerald",   "Medium", "5 hrs", "A tale of wealth, ambition, and lost dreams."}
    };

    String[][] nonFictionBooks = {
        {"Atomic Habits",       "James Clear",        "Easy",   "4 hrs",  "Tiny habits lead to remarkable results over time."},
        {"Sapiens",             "Yuval Noah Harari",  "Hard",   "10 hrs", "A brief history of humankind from ancient times."},
        {"Deep Work",           "Cal Newport",        "Medium", "5 hrs",  "Rules for focused success in a distracted world."},
        {"Rich Dad Poor Dad",   "Robert Kiyosaki",    "Easy",   "4 hrs",  "Financial literacy lessons through real-life stories."},
        {"The Power of Habit",  "Charles Duhigg",     "Medium", "6 hrs",  "Understand why habits exist and how to change them."}
    };

    String[][] comicsBooks = {
        {"Naruto Vol.1",         "Masashi Kishimoto", "Easy",   "2 hrs", "A young ninja dreams of becoming the strongest."},
        {"One Piece Vol.1",      "Eiichiro Oda",      "Easy",   "2 hrs", "Monkey D. Luffy sets sail to find legendary treasure."},
        {"Attack on Titan Vol.1","Hajime Isayama",    "Medium", "2 hrs", "Humanity fights for survival against giant humanoids."},
        {"Death Note Vol.1",     "Tsugumi Ohba",      "Medium", "2 hrs", "A teen discovers a supernatural notebook with deadly power."},
        {"Dragon Ball Vol.1",    "Akira Toriyama",    "Easy",   "2 hrs", "A young boy with a tail goes on an adventure."}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        btnBack         = findViewById(R.id.btnBack);
        tvCategoryTitle = findViewById(R.id.tvCategoryTitle);
        llBookList      = findViewById(R.id.llBookList);

        String category = getIntent().getStringExtra("category");
        tvCategoryTitle.setText(category);
        btnBack.setOnClickListener(v -> finish());

        String[][] books;
        if ("Fiction".equals(category))          books = fictionBooks;
        else if ("Non-Fiction".equals(category)) books = nonFictionBooks;
        else                                     books = comicsBooks;

        for (String[] book : books) {
            Button btn = new Button(this);
            btn.setText(book[0] + "  |  " + book[2]);
            btn.setBackgroundColor(getResources().getColor(android.R.color.white));
            btn.setTextColor(getResources().getColor(android.R.color.black));
            btn.setPadding(24, 24, 24, 24);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 16);
            btn.setLayoutParams(params);

            btn.setOnClickListener(v -> {
                Intent intent = new Intent(this, BookDetailActivity.class);
                intent.putExtra("title",       book[0]);
                intent.putExtra("author",      book[1]);
                intent.putExtra("difficulty",  book[2]);
                intent.putExtra("readingTime", book[3]);
                intent.putExtra("description", book[4]);
                startActivity(intent);
            });

            llBookList.addView(btn);
        }
    }
}
