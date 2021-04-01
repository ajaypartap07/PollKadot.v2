package com.example.pollkadotv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Verification extends AppCompatActivity {

    private Button verifyMang;
    private EditText enterMangPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        verifyMang = (Button)findViewById(R.id.button_verify);
        enterMangPass = (EditText)findViewById(R.id.Verify_editText);

        verifyMang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = enterMangPass.getText().toString().trim();
                String correctPassword = "manager1234";
                if(pass.equals(correctPassword)){
                    Intent intent = new Intent(getApplicationContext(), NewPoll.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Please Enter Correct Password...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}