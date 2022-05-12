package com.example.test03_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//리뷰
//ImageButton - 리뷰버튼, 제안버튼
public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_review);

        Button rev_review = findViewById(R.id.btn_review_review);
        Button rev_report = findViewById(R.id.btn_review_report);
        ImageView rev_pic = findViewById(R.id.imageView);
        rev_pic.setImageResource(R.drawable.disabled_image);

        //layout_review_review으로 화면 전환
        rev_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewWriteActivity.class);
                startActivity(intent);
                Toast.makeText(view.getContext(), "리뷰창 이동", Toast.LENGTH_SHORT).show();
            }
        });

        rev_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewReportActivity.class);
                startActivity(intent);
                Toast.makeText(view.getContext(), "버튼 클릭", Toast.LENGTH_LONG).show();
            }
        });

    }
}