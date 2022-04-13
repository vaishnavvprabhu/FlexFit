package com.vgroup.flexfit.data;

public class blogs {
    private String name;
    private String link;
    private String image;

    public blogs(/*String name, Long time_taken, String desc, String equipment, Long calories, String difficulty, String image*/) {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
