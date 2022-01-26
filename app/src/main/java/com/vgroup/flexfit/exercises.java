package com.vgroup.flexfit;

public class exercises {
    private String name;
    private Long time_taken;
    private String image;

    public exercises(){
//            public exercises(String name, String time_taken, String image){
//            this.name = name;
//            this.time_taken = time_taken;
//            this.image= image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(Long time_taken) {
        this.time_taken = time_taken;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
