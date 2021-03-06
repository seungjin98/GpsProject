package com.example.gpsshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

//    private RecyclerView recyclerView;
//    private RecyclerView.Adapter adapter;
//    private RecyclerView.LayoutManager layoutManager;
//    private ArrayList<User> arrayList;
//    private FirebaseDatabase database;
//    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LinearLayout pastaLayout = (LinearLayout) findViewById(R.id.iv_profile);
        LinearLayout nongbuLayout = (LinearLayout) findViewById(R.id.iv_profile2);
        LinearLayout modulangLayout = (LinearLayout) findViewById(R.id.iv_profile3);
        LinearLayout seowonLayout = (LinearLayout) findViewById(R.id.iv_profile4);
        LinearLayout sunsaltteogbokkLayout = (LinearLayout) findViewById(R.id.iv_profile5);
        LinearLayout tonkasuLayout = (LinearLayout) findViewById(R.id.iv_profile6);
        LinearLayout hotdogLayout = (LinearLayout) findViewById(R.id.iv_profile7);
        LinearLayout bolibabLayout = (LinearLayout) findViewById(R.id.iv_profile8);
        LinearLayout sundeagugLayout = (LinearLayout) findViewById(R.id.iv_profile9);
        LinearLayout gobkkaebiLayout = (LinearLayout) findViewById(R.id.iv_profile10);

        pastaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, pasta1970.class);
                startActivity(intent);
            }
        });

        nongbuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, nongbubossam.class);
                startActivity(intent);
            }
        });
        modulangLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, modulangbunsig.class);
                startActivity(intent);
            }
        });
        seowonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, seowon.class);
                startActivity(intent);
            }
        });
        sunsaltteogbokkLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, sunsaltteogbokk.class);
                startActivity(intent);
            }
        });
        tonkasuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, tonkasu.class);
                startActivity(intent);
            }
        });
        hotdogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, hotdog.class);
                startActivity(intent);
            }
        });
        bolibabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, bolibab.class);
                startActivity(intent);
            }
        });
        sundeagugLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, sundaegug.class);
                startActivity(intent);
            }
        });
        gobkkaebiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, gobkkaebi.class);
                startActivity(intent);
            }
        });



//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        arrayList = new ArrayList<>();
//
//        database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference("User");//db??????
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //?????????????????? ????????????????????? ???????????????
//                arrayList.clear();//???????????? ?????????
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    User user = snapshot.getValue(User.class);//???????????? ????????? ???????????? ?????????
//                    arrayList.add(user);//?????? ???????????? ????????? ?????? ????????????????????? ????????????
//                }
//                adapter.notifyDataSetChanged();//????????? ?????? ??? ?????? ??????
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e("ListActivity", String.valueOf(databaseError.toException()));//????????? ??????
//            }
//        });
//
//        adapter = new CustomAdapter(arrayList, this);
//        recyclerView.setAdapter(adapter);//??????
//
    }
}


