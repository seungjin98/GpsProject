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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth nFirebaseAuth;     //파이어 베이스 인증
    private DatabaseReference nDatabaseRef; // 실시간 데이타베이스
    private EditText mEtEmail, mEtPwd, mEtAge, mEtName, mEtTel;      // 회원가입 입력필드
    private Button nBtnRegister;            // 회원가입 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nFirebaseAuth = FirebaseAuth.getInstance();
        nDatabaseRef = FirebaseDatabase.getInstance().getReference("gpsshop");

        mEtEmail =findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtAge = findViewById(R.id.et_age);
        mEtName = findViewById(R.id.et_name);
        mEtTel = findViewById(R.id.et_tel);
        nBtnRegister = findViewById(R.id.btn_register);

        nBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                String strAge = mEtAge.getText().toString();
                String strName = mEtName.getText().toString();
                String strTel = mEtTel.getText().toString();

                // Firebase Auth 진행
                nFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = nFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId(firebaseUser.getEmail());
                            account.setPassword(strPwd);
                            account.setName(strName);
                            account.setAge(strAge);
                            account.setTel(strTel);

                            // setValue : database에 insert(삽입) 행위
                            nDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}