package com.example.test03_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity

        implements OnMapReadyCallback {
    private GoogleMap mMap;
    private EditText mEditName;
    private EditText mEditAge;
    private TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditName = findViewById(R.id.editName);
        mEditAge = findViewById(R.id.editAge);
        mTextResult = findViewById(R.id.textResult);
        Button map = findViewById(R.id.btn_map);
        Button conv = findViewById(R.id.btn_conv);
        Button review = findViewById(R.id.btn_review);

        //layout_map으로 화면 전환
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //layout_conv으로 화면 전환
        conv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConvActivity.class);
                startActivity(intent);
            }
        });

        //layout_review으로 화면 전환
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);
            }
        });

        //SupportMapFragment를 통해 레이아웃(layout_map.xml)에 만든 fragment ID를 참조하고 구글맵을 호출한다.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Google Map (위치지정, 마커표시)
    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;

        LatLng GWNU = new LatLng(37.3055347, 127.9225158);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(GWNU);
        markerOptions.title("강릉원주대학교");
        mMap.addMarker(markerOptions);

//        정문<->W6 경로 표시
        PolylineOptions FrontDoor_W6 = new PolylineOptions()
                .add(
                        new LatLng(37.30625212, 127.92448392),
                        new LatLng(37.30625212, 127.9244604),
                        new LatLng(37.30538937, 127.92661116),
                        new LatLng(37.330564343, 127.92232482)
                );

        RadioGroup rg = findViewById(R.id.radioGroup); //라디오 그룹

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //정문-W6 버튼 클릭시 해당 경로 출력
                if(radioGroup.getId() == R.id.radioGroup){
                    switch (i){
                        case R.id.radio1:
                            Polyline polyline = mMap.addPolyline(FrontDoor_W6);
                            break;
                        case R.id.radio2:
                            Toast.makeText(getApplicationContext(), "후문-w6 test", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

            }
        });

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GWNU, 17));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    //네비게이션 기능
    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

}