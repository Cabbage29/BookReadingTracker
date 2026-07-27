package com.example.bookreader;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    Button btnBack;
    LinearLayout llSummaryList;
    TextView tvTotalBooks, tvTotalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        btnBack       = findViewById(R.id.btnBack);
        llSummaryList = findViewById(R.id.llSummaryList);
        tvTotalBooks  = findViewById(R.id.tvTotalBooks);
        tvTotalTime   = findViewById(R.id.tvTotalTime);

        btnBack.setOnClickListener(v -> finish());

        int totalBooks = BookDetailActivity.readingLog.size();
        int totalHours = 0;

        for (String[] book : BookDetailActivity.readingLog) {
            TextView tv = new TextView(this);
            tv.setText("📖 " + book[0] + " by " + book[1]
                    + "\n   Difficulty: " + book[2]
                    + "  |  Time: " + book[3]);
            tv.setTextSize(14);
            tv.setPadding(16, 16, 16, 16);
            tv.setBackgroundColor(getResources().getColor(android.R.color.white));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 12);
            tv.setLayoutParams(params);
            llSummaryList.addView(tv);

            try {
                totalHours += Integer.parseInt(book[3].replace(" hrs", "").trim());
            } catch (NumberFormatException ignored) {}
        }

        tvTotalBooks.setText("Total Books Logged: " + totalBooks);
        tvTotalTime.setText("Total Reading Time: " + totalHours + " hrs");
    }
}
