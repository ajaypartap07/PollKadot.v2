package com.example.pollkadotv2;

import java.io.Serializable;


public class PollInformation implements Serializable {

    public String question_number;
    public String question;
    public String description;
    public String option1;
    public String option2;

    public PollInformation(){


    }

    @Override
    public String toString() {
        return "P_{" + question_number ;
    }

    public PollInformation(String question_number, String question, String description, String option1, String option2) {
        this.question_number=question_number;
        this.question = question;
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
    }


    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption1() {

        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getQuestion_number() {
        return question_number;
    }

    public String getQuestion() {
        return question;
    }

    public String getDescription() {
        return description;
    }

    public void setQuestion_number(String question_number) {
        this.question_number = question_number;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
