package com.example.gpsshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap googleMap;
//    Button btn1;
    private TextView memberName;
    private List<UserAccount> accounts = new ArrayList<>();
    private FirebaseDatabase database;
    private DrawerLayout drawerLayout;
    private View drawerView;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        btn1 = findViewById(R.id.button2);
//        btn1.setOnClickListener(this);

        // 데이터 베이스에서 회원 정보 가져오기
//        database = FirebaseDatabase.getInstance();
//        database.getReference().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot snapshot1 : snapshot.getChildren()){
//                    UserAccount account = snapshot.getValue(UserAccount.class);
//                    memberName.setText(account.getEmailId());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        // 회원 아이디 가져오기
        Intent intent =getIntent();
        String member_id = intent.getStringExtra("member_Id");


        // drawerLayout 부분
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        TextView loginName = (TextView)findViewById(R.id.loginName);
        loginName.setText(member_id);

        //로그아웃 하기
        Button btn_logout = (Button)findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // 지도로 이동
        Button btn_maps = (Button)findViewById(R.id.btn_map);
        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // 점포 목록으로 이동
        Button btn_shop = (Button)findViewById(R.id.btn_shop);
        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        Button btn_open = (Button)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        Button btn_close = (Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });



    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng latLng = new LatLng(37.58682092450221, 127.09767932500326);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));


        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("서일대학교");
        googleMap.addMarker(markerOptions);

        LatLng hotdog = new LatLng(37.58758575510095, 127.09725251204158);
        Marker hotdogMarker1 = googleMap.addMarker(new MarkerOptions().position(hotdog).title("스테프핫도그").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotdogicon)));

        LatLng modulang = new LatLng(37.5878356379744, 127.09659900771643);
        Marker modulangMarker =googleMap.addMarker(new MarkerOptions().position(modulang).title("모두랑분식").icon(BitmapDescriptorFactory.fromResource(R.drawable.bunsigicon)));

        LatLng nongbu = new LatLng(37.586408079453356, 127.0949109095879);
        Marker nongbuMarker =googleMap.addMarker(new MarkerOptions().position(nongbu).title("농부보쌈").icon(BitmapDescriptorFactory.fromResource(R.drawable.meaticon)));

        LatLng pasta1970 = new LatLng(37.58598060214817, 127.09511556519804);
        Marker pasta1970Marker =googleMap.addMarker(new MarkerOptions().position(pasta1970).title("1970pasta").icon(BitmapDescriptorFactory.fromResource(R.drawable.nodle1icon)));

        LatLng seowon = new LatLng(37.5906657688273, 127.09737925527702);
        Marker seowongMarker =googleMap.addMarker(new MarkerOptions().position(seowon).title("서원").icon(BitmapDescriptorFactory.fromResource(R.drawable.noodleicon)));

        LatLng sunsal = new LatLng(37.59042528195185, 127.09335338745953);
        Marker sunsalMarker =googleMap.addMarker(new MarkerOptions().position(sunsal).title("마단순살떡볶이").icon(BitmapDescriptorFactory.fromResource(R.drawable.bunsigicon)));

        LatLng tonkasu = new LatLng(37.58814980781004, 127.0968265135842);
        Marker tonkasuMarker =googleMap.addMarker(new MarkerOptions().position(tonkasu).title("가나점보돈가스").icon(BitmapDescriptorFactory.fromResource(R.drawable.bunsigicon)));

        LatLng bolibab = new LatLng(37.58923871233086, 127.09644379938139);
        Marker bolibabMarker =googleMap.addMarker(new MarkerOptions().position(bolibab).title("시골 보리밥").icon(BitmapDescriptorFactory.fromResource(R.drawable.riceicon)));

        LatLng sundeagug = new LatLng(37.59000004088219, 127.09160498100121);
        Marker sundeagugMarker =googleMap.addMarker(new MarkerOptions().position(sundeagug).title("소문난 순대국").icon(BitmapDescriptorFactory.fromResource(R.drawable.riceicon)));

        LatLng gobkkaebi = new LatLng(37.58998179649499, 127.0952900422118);
        Marker gobkkaebiMarker =googleMap.addMarker(new MarkerOptions().position(gobkkaebi).title("곱깨비").icon(BitmapDescriptorFactory.fromResource(R.drawable.meaticon)));

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                if(marker.equals(hotdogMarker1)) {
                    Intent intent = new Intent(MainActivity.this, hotdog.class);
                    startActivity(intent);
                } else if(marker.equals(modulangMarker)) {
                    Intent intent = new Intent(MainActivity.this, modulangbunsig.class);
                    startActivity(intent);
                }else if(marker.equals(nongbuMarker)) {
                    Intent intent = new Intent(MainActivity.this, nongbubossam.class);
                    startActivity(intent);
                }else if(marker.equals(pasta1970Marker)) {
                    Intent intent = new Intent(MainActivity.this, pasta1970.class);
                    startActivity(intent);
                }else if(marker.equals(seowongMarker)) {
                    Intent intent = new Intent(MainActivity.this, seowon.class);
                    startActivity(intent);
                }else if(marker.equals(sunsalMarker)) {
                    Intent intent = new Intent(MainActivity.this, sunsaltteogbokk.class);
                    startActivity(intent);
                }else if(marker.equals(tonkasuMarker)) {
                    Intent intent = new Intent(MainActivity.this, tonkasu.class);
                    startActivity(intent);
                }else if(marker.equals(bolibabMarker)) {
                    Intent intent = new Intent(MainActivity.this, bolibab.class);
                    startActivity(intent);
                }else if(marker.equals(sundeagugMarker)) {
                    Intent intent = new Intent(MainActivity.this, sundaegug.class);
                    startActivity(intent);
                }else if(marker.equals(gobkkaebiMarker)) {
                    Intent intent = new Intent(MainActivity.this, gobkkaebi.class);
                    startActivity(intent);
                }
            }
        });

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            checkLocationPermissionWithRationale();
        }

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


//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_LOCATION: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                        googleMap.setMyLocationEnabled(true);
//                    }
//                } else {
//                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
//                }
//                return;
//            }
//        }
//    }



//    @Override
//    public void onClick(View v) {
//        Intent intent1 = new Intent(this, MainActivity2.class);
//        startActivity(intent1);
//    }
}