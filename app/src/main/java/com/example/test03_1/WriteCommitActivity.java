package com.example.test03_1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//리뷰 등록시 나타나는 화면
//리뷰 -> 리뷰하기 -> 등록하기
public class WriteCommitActivity  extends AppCompatActivity {
//    private ListView listview;
    private Button listAdd;
    private TextView DataReview;
    private Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_review_commit);

        TextView ttitle = findViewById(R.id.review_title);
        TextView trating = findViewById(R.id.review_rating);
        TextView tcontents = findViewById(R.id.review_contents);
        Button listAdd = findViewById(R.id.listAdd);

        Intent intent = getIntent();

//        bundle을 통해 getExtras에 있는 값을 모두 가져온다.
        Bundle bundle = intent.getExtras();

//        키 값을 통해 extra에 있는 값을 읽어온다.
        String title = intent.getStringExtra("title");
        String rating = intent.getStringExtra("rating");
        String contents = intent.getStringExtra("contents");
        
        ttitle.setText(title);
        trating.setText(rating+"/5");
        tcontents.setText(contents);

        //새글작성 버튼 클릭스 이벤트 처리
        listAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewWriteActivity.class);
                Toast.makeText(view.getContext(), "새로 작성", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
