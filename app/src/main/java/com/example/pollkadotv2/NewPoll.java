package com.example.pollkadotv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPoll extends AppCompatActivity {

    private Button post;
    private EditText question, description;
    private EditText option1,option2;
    private DatabaseReference mDatabaseReference;
    public PollInformation pollInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poll);

        question= (EditText) findViewById(R.id.question);
        description= (EditText) findViewById(R.id.description);
        option1= (EditText) findViewById(R.id.option1);
        option2= (EditText) findViewById(R.id.option2);

        post=(Button)findViewById(R.id.btnpost);
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("PollQuestions");

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInformation();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mDatabaseReference= FirebaseDatabase.getInstance().getReference("PollQuestions");
    }

    private void addInformation(){
        String question_string= question.getText().toString().trim();
        String description_string= description.getText().toString().trim();
        String option1_string= option1.getText().toString().trim();
        String option2_string= option2.getText().toString().trim();



        if(!TextUtils.isEmpty(question_string)){

            if(!TextUtils.isEmpty(option1_string)) {

                if(!TextUtils.isEmpty(option2_string)) {
                    String id = mDatabaseReference.push().getKey();
                    pollInformation = new PollInformation(id, question_string, description_string, option1_string, option2_string);
                    mDatabaseReference.child(id).setValue(pollInformation);
                    Toast.makeText(this, "Question Added. ", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this ,"Enter your Option 2 ", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this ,"Enter your Option 1 ", Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(this ,"Enter your Question! ", Toast.LENGTH_SHORT).show();
        }

    }
}
