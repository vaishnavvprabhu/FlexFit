package com.vgroup.flexfit.data;

public class exercises {
    private String name;
    private Long time_taken;
    private String desc;
    private String equipment;
    private Long calories;
    private String difficulty;
    private String image;

    public exercises(/*String name, Long time_taken, String desc, String equipment, Long calories, String difficulty, String image*/) {
//
      /*      this.name = name;
            this.time_taken = time_taken;
            this.desc = desc;
            this.equipment = equipment;
            this.difficulty = difficulty;
            this.image= image;
            this.calories = calories;*/
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
