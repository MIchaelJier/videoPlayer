package com.team5101.entity;

public class VQe {
    private Integer id;
    private String videoname;
    private Integer qtime;
    private String question;
    private String ans;

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public Integer getQtime() {
        return qtime;
    }

    public void setQtime(Integer qtime) {
        this.qtime = qtime;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }



}
