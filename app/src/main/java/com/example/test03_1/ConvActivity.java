package com.example.test03_1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

// 편의시설
public class ConvActivity extends AppCompatActivity  implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText mEditName;
    private EditText mEditAge;
    private TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_conv);

        mEditName = findViewById(R.id.editName);
        mEditAge = findViewById(R.id.editAge);
        mTextResult = findViewById(R.id.textResult);

        //SupportMapFragment를 통해 레이아웃(layout_map.xml)에 만든 fragment ID를 참조하고 구글맵을 호출한다.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        CheckBox toilet = findViewById(R.id.checkbox_toilet);
        CheckBox elevator = findViewById(R.id.checkbox_elevator);
        CheckBox hospital = findViewById(R.id.checkbox_hospital);
        CheckBox rest = findViewById(R.id.checkbox_rest);

        //화장실에 체크했을 때
        toilet.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    MarkerOptions toiletMark = new MarkerOptions();
                    toiletMark.position(new LatLng(37.30620559032, 127.9244931657));
                    toiletMark.title("화장실");
                    mMap.addMarker(toiletMark);
                }else{
                    mMap.clear();
                }
            }
        });

        //엘레베이터에 체크했을 때
        elevator.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    MarkerOptions elevatorMark = new MarkerOptions();
                    elevatorMark.position(new LatLng(37.30620559032, 127.9244931657));
                    elevatorMark.title("엘레베이터");
                    mMap.addMarker(elevatorMark);
                }else{
                    mMap.clear();
                }
            }
        });

        //병원 체크했을 때
        hospital.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    MarkerOptions hopitalMark = new MarkerOptions();
                    hopitalMark.position(new LatLng(36.963880825057295, 127.94290388797995 ));
                    hopitalMark.title("병원마커");
                    mMap.addMarker(hopitalMark);
                }else{
                    mMap.clear();
                }
            }
        });

        //쉼터 체크했을 때
        rest.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    MarkerOptions restMark = new MarkerOptions();
                    restMark.position(new LatLng(37.40620559032, 127.8244931657));
                    restMark.title("쉼터마커");
                    mMap.addMarker(restMark);
                }else{
                    mMap.clear();
                }
            }
        });

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