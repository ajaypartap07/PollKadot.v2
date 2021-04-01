package com.example.pollkadotv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PollCount extends AppCompatActivity {

    public TextView Question;
    public TextView Description, Counter1, Counter2;
    public RadioButton Option1;
    public RadioButton Option2;
    public EditText VoteCasterEmail;
    public EditText VoteCasterName;
    public Button VoteCasterBtn;
    public EditText VoteCastedTo;
    public String userID;
    public String userEmail;
    public DatabaseReference mDatabaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_count);

        Question = (TextView) findViewById(R.id.q);
        Description = (TextView) findViewById(R.id.d);
        Option1 = (RadioButton) findViewById(R.id.o1);
        Option2 = (RadioButton) findViewById(R.id.o2);
        VoteCasterBtn = (Button) findViewById(R.id.VoteCasterBtn);


        //optionName = Option1.getText().toString();

        Question.setText(getIntent().getStringExtra("textViewQuestion"));
        Description.setText(getIntent().getStringExtra("textViewDescription"));
        Option1.setText(getIntent().getStringExtra("textViewOption1"));
        Option2.setText(getIntent().getStringExtra("textViewOption2"));
        //VotOption1.setText(getIntent().getStringExtra("VotOption1"));
        //VotOption2.setText(getIntent().getStringExtra("VotOption2"));


    }

    public void process(View view) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        String Option1Dat = Option1.getText().toString().trim();
        String Option2Dat = Option2.getText().toString().trim();
        String questionAsked = Question.getText().toString().trim();
        userID = FirebaseAuth.getInstance().getUid();
        userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Member obj;
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("PollQuestions").child(questionAsked);
        String pollkey = mDatabaseReference.getRef().getKey();


        if (Option1.isChecked()) {
            obj = new Member(questionAsked, userEmail, Option1Dat);
        } else {
            obj = new Member(questionAsked, userEmail, Option2Dat);
        }


        DatabaseReference node1 = db.getReference("UserVotes");

        node1.child(pollkey).child(userID).setValue(obj);
        Toast.makeText(this, "Vote Added to Database.", Toast.LENGTH_SHORT).show();


    }



}

