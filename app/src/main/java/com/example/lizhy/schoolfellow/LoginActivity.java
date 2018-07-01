package com.example.lizhy.schoolfellow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText log_user;
    private EditText log_pw;
    private Button login;
    private Button to_reg;
    private String user;
    private String pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        log_user = (EditText)findViewById(R.id.log_user);
        log_pw = (EditText)findViewById(R.id.log_pw);
        login = (Button)findViewById(R.id.login);
        to_reg = (Button)findViewById(R.id.to_reg);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = log_user.getText().toString();
                pw = log_pw.getText().toString();
                LoginRequest(user,pw);
            }
        });
        to_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(it2);
            }
        });

    }

    /*需与服务器通讯*/
    public void LoginRequest(final String user,final String pw){
        Intent it2 = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(it2);
    }
}
