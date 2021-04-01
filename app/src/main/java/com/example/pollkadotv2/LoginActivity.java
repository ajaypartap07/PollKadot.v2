package com.example.pollkadotv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextUname;
    private TextView textViewSignup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            //profileacitvity
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivityNavBar.class));
        }


        buttonSignIn = (Button) findViewById(R.id.buttonSignin);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);
        textViewSignup=(TextView)findViewById(R.id.textViewSignUp);
        progressDialog=new ProgressDialog(this);


        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }



    private void userLogin(){

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

        progressDialog.setMessage("Logging in Please wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    //start profile activity

                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivityNavBar.class));



                }


            }
        });


    }

    @Override
    public void onClick(View view) {

        if(view==buttonSignIn){
            userLogin();
        }
        if(view==textViewSignup){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }


    }
}