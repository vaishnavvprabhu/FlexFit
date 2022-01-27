package com.vgroup.flexfit.data;

public class exercises {
    private String name;
    private Long time_taken;
    private String desc;
    private String equipment;
    private Long calories;
    private String difficulty;
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


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
