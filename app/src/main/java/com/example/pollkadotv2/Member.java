package com.example.pollkadotv2;

public class Member {
    private String questionAsked;
    private String address;

    public String getVotedFor() {
        return votedFor;
    }

    public void setVotedFor(String votedFor) {
        this.votedFor = votedFor;
    }

    private String votedFor;

    public Member(String questionAsked, String address, String votedFor) {
        this.questionAsked = questionAsked;
        this.address = address;
        this.votedFor = votedFor;
    }

    public String getquestionAsked() {
        return questionAsked;
    }

    public void setquestionAsked(String questionAsked) {
        this.questionAsked = questionAsked;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
