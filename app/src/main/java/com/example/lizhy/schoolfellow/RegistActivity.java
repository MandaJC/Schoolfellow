package com.example.lizhy.schoolfellow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lizhy on 2018/6/23.
 */

public class RegistActivity extends AppCompatActivity {

    private EditText reg_user;
    private EditText reg_pw1;
    private EditText reg_pw2;
    private Button reg;
    private String user;
    private String pw1;
    private String pw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        reg_user = (EditText)findViewById(R.id.reg_user);
        reg_pw1 = (EditText)findViewById(R.id.reg_pw1);
        reg_pw2 = (EditText)findViewById(R.id.reg_pw2);
        reg = (Button)findViewById(R.id.register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = reg_user.getText().toString();
                pw1 = reg_pw1.getText().toString();
                pw2 = reg_pw2.getText().toString();
                if(!pw1.equals(pw2)){
                    Toast.makeText(RegistActivity.this,"密码不一致", Toast.LENGTH_LONG).show();
                }
                else{RegistRequest(user,pw1);}
            }
        });
    }
    /*需与服务器通讯*/
    public void RegistRequest(final String user,final String pw){
        Intent it = new Intent(RegistActivity.this,LoginActivity.class);
        startActivity(it);
    }
}
