package com.example.pollkadotv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class QuestionList extends ArrayAdapter<PollInformation> {

    public Activity context;
    public List<PollInformation> questionList;

    public QuestionList(Activity context, List<PollInformation> questionList){
        super(context, R.layout.list_layout,questionList);
        this.context=context;
        this.questionList=questionList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);
        convertView= listViewItem;

        TextView textViewQuestion= (TextView)listViewItem.findViewById(R.id.textViewQuestion);
        TextView textViewDescription= (TextView)listViewItem.findViewById(R.id.textViewDescription);
        TextView textViewOption1= (TextView)listViewItem.findViewById(R.id.textViewOption1);
        TextView textViewOption2= (TextView)listViewItem.findViewById(R.id.textViewOption2);
        //TextView VotOption1= (TextView)listViewItem.findViewById(R.id.votOpt1);
        //TextView VotOption2= (TextView)listViewItem.findViewById(R.id.votOpt2);

        PollInformation pollInformation = questionList.get(position);

        final String question= pollInformation.getQuestion();
        final String description= pollInformation.getDescription();
        final String option1=pollInformation.getOption1();
        final String option2=pollInformation.getOption2();


        textViewQuestion.setText(pollInformation.getQuestion());
        textViewDescription.setText(pollInformation.getDescription());
        textViewOption1.setText(pollInformation.getOption1());
        textViewOption2.setText(pollInformation.getOption2());


        listViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openPollCount(question,description,option1,option2);

            }
        });



        return listViewItem;
    }

    public void openPollCount(String question, String description, String option1, String option2)
    {

        Intent i= new Intent(context, PollCount.class);
        i.putExtra("textViewQuestion", question);
        i.putExtra("textViewDescription", description);
        i.putExtra("textViewOption1", option1);
        i.putExtra("textViewOption2", option2);

        context.startActivity(i);

    }
}

