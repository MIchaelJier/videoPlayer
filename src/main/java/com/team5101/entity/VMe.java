package com.team5101.entity;

public class VMe {
    private Integer id;
    private String videoname;
    private String videosrc;
    private String picsrc;

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

    public String getVideosrc() {
        return videosrc;
    }

    public void setVideosrc(String videosrc) {
        this.videosrc = videosrc;
    }

    public String getPicsrc() {
        return picsrc;
    }

    public void setPicsrc(String picsrc) {
        this.picsrc = picsrc;
    }

    public String getVideointroduce() {
        return videointroduce;
    }

    public void setVideointroduce(String videointroduce) {
        this.videointroduce = videointroduce;
    }

    private String videointroduce;
}
