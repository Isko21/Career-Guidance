package com.example.careerguidance.Others;

public class Question {
    int id;
    String personality, question;
    public Question(int _id, String _personality, String _question){
        this.id = _id;
        this.personality = _personality;
        this.question = _question;
    }
    public int getId(){
        return id;
    }
    public void setId(int _id){
        this.id = _id;
    }
    public String getPersonality(){
        return personality;
    }
    public void setPersonality(String _personality){
        this.personality = _personality;
    }
    public String getQuestion(){
        return question;
    }
    public void setQuestion(String _question){
        this.question = _question;
    }
}
