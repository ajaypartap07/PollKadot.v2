package com.example.pollkadotv2;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private FirebaseAuth firebaseAuth;
    private EditText editTextUname;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            //profileacitvity

            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivityNavBar.class));
        }
        progressDialog=new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);
        textViewSignin=(TextView)findViewById(R.id.textViewSignin);


        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);



    }

    private void registerUser(){

        // getting email and password
        String email= editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter email",Toast.LENGTH_SHORT).show();
            return;


        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter password",Toast.LENGTH_SHORT).show();
            return;

        }



        progressDialog.setMessage("Registering Please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //user logged in
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivityNavBar.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Try Again",Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view==buttonRegister){
            registerUser();
        }
        if(view==textViewSignin){
            startActivity(new Intent(this,LoginActivity.class));

        }

    }
}
