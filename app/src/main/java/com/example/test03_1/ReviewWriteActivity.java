package com.example.test03_1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;

//리뷰 -> 리뷰하기
//버튼 - 취소, 확인
public class ReviewWriteActivity extends AppCompatActivity {

    private EditText title;
    private RatingBar rating;
    private EditText contents;
    private Button commit;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_review_review);

        Button cancel = findViewById(R.id.review_cancel);
        commit = findViewById(R.id.review_commit);
        title = findViewById(R.id.titleEdit);
        rating = findViewById(R.id.reviewRating);
        contents = findViewById(R.id.contentsEdit);
//        취소시 review창으로 되돌아감
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);
            }
        });
//등록시 게시판으로 이동
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WriteCommitActivity.class);
                Toast.makeText(view.getContext(), "등록 완료", Toast.LENGTH_SHORT).show();

                String rate = String.valueOf(rating.getNumStars());
//        editText에 작성한 값을 Text로 가져와서 String으로 변환
//                putExtra 메서드를 통해 키 값과 데이터 저장
                intent.putExtra("title",title.getText().toString());
                intent.putExtra("rating", rate);
                intent.putExtra("contents",contents.getText().toString());

                startActivity(intent);
            }
        });
    }

}