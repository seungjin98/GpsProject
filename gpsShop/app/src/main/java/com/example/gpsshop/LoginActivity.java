package com.example.gpsshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth nFirebaseAuth; //파이어베이스 인증
    private DatabaseReference nDatabaseRef; //실시간 데이터 베이스
    private EditText  editId, editPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nFirebaseAuth = FirebaseAuth.getInstance();
        nDatabaseRef = FirebaseDatabase.getInstance().getReference("gpsshop");

        editId = findViewById(R.id.et_id1);
        editPwd = findViewById(R.id.et_pwd1);


        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this,MainActivity2.class);
                startActivity(intent);

            }
        });

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                //로그인 요청
//                String strEmail = editId.getText().toString();
//                String strPwd = editPwd.getText().toString();
//
//                nFirebaseAuth.signInWithEmailAndPassword(strEmail,strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            //로그인 성공
//                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                            startActivity(intent);
//                        } else {
//                            //로그인 실패
//                            Toast.makeText((LoginActivity.this), "로그인 실패", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

            }
        });
    }
}