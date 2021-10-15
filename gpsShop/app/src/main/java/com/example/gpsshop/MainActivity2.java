package com.example.gpsshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
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

public class MainActivity2 extends AppCompatActivity {

    private FirebaseAuth nFirebaseAuth; //파이어베이스 인증
    private DatabaseReference nDatabaseRef; //실시간 데이터 베이스
    private EditText editName, editId, editPwd, editAge;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nFirebaseAuth = FirebaseAuth.getInstance();
        nDatabaseRef = FirebaseDatabase.getInstance().getReference("gpsshop");

        editName = findViewById(R.id.et_name);
        editId = findViewById(R.id.et_id);
        editPwd = findViewById(R.id.et_pwd);
        editAge = findViewById(R.id.et_age);

        btnRegister = findViewById(R.id.btn_member_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 시작
                String strEmail = editId.getText().toString();
              //  String strName = editName.getText().toString();
                String strPwd = editPwd.getText().toString();
              //  String strAge = editAge.getText().toString();

                nFirebaseAuth.createUserWithEmailAndPassword(strEmail,strPwd).addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = nFirebaseAuth.getCurrentUser();

                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmail(firebaseUser.getEmail());
                            account.setPwd(strPwd);
                            //account.setAge(strAge);
                            //account.setName(strName);

                            nDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(MainActivity2.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();
                        }   else {
                            Toast.makeText(MainActivity2.this, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

}