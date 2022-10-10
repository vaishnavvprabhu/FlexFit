package com.vgroup.flexfit.data;

//Author : VVP
public class User {

        String name;
        String email;
        String useridentity;
        double age;
        double weight;
        double height;
        double BMI;
        String BMI_range;
        String pref_workout;

        public User(String name, String email, String useridentity, double age, double weight, double height, double BMI, String BMI_range, String pref_workout) {
                this.name = name;
                this.email = email;
                this.useridentity = useridentity;
                this.age = age;
                this.weight = weight;
                this.height = height;
                this.BMI = BMI;
                this.BMI_range = BMI_range;
                this.pref_workout = pref_workout;
        }

        public String getName() {return name;}
        public String getEmail() {
                return email;
        }
        public String getUseridentity() {
                return useridentity;
        }

        public double getAge() {
                return age;
        }

        public double getWeight() {
                return weight;
        }

        public double getHeight() {
                return height;
        }

        public double getBMI() {
                return BMI;
        }

        public String getBMI_range() {
                return BMI_range;
        }

        public String getPref_workout() {
                return pref_workout;
        }
}
