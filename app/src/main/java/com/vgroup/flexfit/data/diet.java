package com.vgroup.flexfit.data;
//Author : VVP
public class diet {
    private String image;
    private String name;
    private Long calories;
    private String difficulty;
    private String step1;
    private String step2;
    private String step3;

    public diet(/*String image, String name, Long calories, String difficulty, String step1, String step2, String step3*/) {
/*        this.image = image;
        this.name = name;
        this.calories = calories;
        this.difficulty = difficulty;
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;*/
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStep1() {
        return step1;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public String getStep2() {
        return step2;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }

    public String getStep3() {
        return step3;
    }

    public void setStep3(String step3) {
        this.step3 = step3;
    }
}
