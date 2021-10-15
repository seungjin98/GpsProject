package com.example.gpsshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
/**, View.OnClickListener**/ {

    GoogleMap googleMap;
//    MarkerOptions markerOptions;
//    double Latitude,Longitude; //위도경도
//    Button btn1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        btn1 = findViewById(R.id.button2);
//        btn1.setOnClickListener(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /*
 지도화면이 출려된 후에 이벤트를 연결할 수 있어야 핟. 지도를 클릭하거나 지도를 드래그, 줌레벨 버튼을 이용해서 줌레벨을 변경
    - OnMapClickListener => 지도를 클릭할 때 발생하는 이벤트에 대한 처리
    - OnMapLongClickListener => 맵을 길게 눌렀을 때 이벤트에 대한 처리
    - OnCameraMoveListener => 지도의 위치가 바뀌거나 줌레벨이 변경되어 카메라가 이동될 때 이벤트에 대한 처리
    - OnCameraMoveStartedListener => 지도의 위치가 바뀌거나 줌레벨이 변경되어 카메라가 이동되기 시작할 때 이벤트에 대한 처리
        [순서] OnCameraMoveStartedListener->OnCameraMoveListener
*/


    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        //서일대 37.58682092450221, 127.09767932500326
        //https://api.odcloud.kr/api/15035759/v1/uddi:2279f284-e6b4-47b5-92bc-f20bbdda8a19_201908221042?page=1&perPage=10
        LatLng latLng = new LatLng(37.58682092450221, 127.09767932500326);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("서일대학교");
        googleMap.addMarker(markerOptions);
        googleMap.getUiSettings().setZoomControlsEnabled(true);//줌 +,-버튼
//        **googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            checkLocationPermissionWithRationale();
        }

    }

    public void getLoction(double lat, double lng){

        
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermissionWithRationale() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("위치정보")
                        .setMessage("이 앱을 사용하기 위해서는 위치정보에 접근이 필요합니다. 위치정보 접근을 허용하여 주세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        googleMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }



//    @Override
//    public void onClick(View v) {
//        Intent intent1 = new Intent(this, MainActivity2.class);
//        startActivity(intent1);
//    }
}