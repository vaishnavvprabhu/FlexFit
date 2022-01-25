package com.vgroup.flexfit.data;

public class FetchExercises {
    private String name;
    private String time_taken;
    private String image;

    public FetchExercises(String name, String time_taken, String image){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(String time_taken) {
        this.time_taken = time_taken;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
