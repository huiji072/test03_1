package com.example.test03_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//리뷰 -> 제보하기
//Button - 취소, 제보
public class ReviewReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_review_report);

        Button cancel = findViewById(R.id.report_cancel);
        Button commit = findViewById(R.id.report_commit);
        EditText title_report = findViewById(R.id.editTextTextMultiLine);
        EditText contents_report = findViewById(R.id.editTextTextMultiLine2);

//        취소시 review창으로 되돌아감
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);
            }
        });

        //등록시 ReportCommit 창으로 값과 함께 이동
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ReportCommitActivity.class);
                startActivity(intent);
                Toast.makeText(view.getContext(), "등록 완료", Toast.LENGTH_SHORT).show();

//        editText에 작성한 값을 Text로 가져와서 String으로 변환
//                putExtra 메서드를 통해 키 값과 데이터 저장
                intent.putExtra("report",contents_report.getText().toString());
                intent.putExtra("title",title_report.getText().toString());
                startActivity(intent);
            }
        });

    }
}