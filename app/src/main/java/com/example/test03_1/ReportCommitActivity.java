package com.example.test03_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//제보하기 등록시 나타나는 화면
//리뷰 -> 제안하기 -> 등록
public class ReportCommitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_report_commit);

        TextView text = findViewById(R.id.view_report_commit);
        TextView text2 = findViewById(R.id.view_report_commit2);

        Intent intent = getIntent();
//        bundle을 통해 getExtras에 있는 값을 모두 가져온다.
        Bundle bundle = intent.getExtras();
//        키 값을 통해 extra에 있는 값을 읽어온다.
        String title = intent.getStringExtra("title");
        String contents = intent.getStringExtra("report");

        //TextView text에 title과 contents 출력
        text.setText(title);
        text2.setText(contents);


    }
}
