package com.vgroup.flexfit.data;

public class User {

        String email;
        String useridentity;
        int age;
        int weight;
        int height;
        double BMI;
        String BMI_range;
        String pref_workout;

        public User(String email, String useridentity, int age, int weight, int height, double BMI, String BMI_range, String pref_workout) {
                this.email = email;
                this.useridentity = useridentity;
                this.age = age;
                this.weight = weight;
                this.height = height;
                this.BMI = BMI;
                this.BMI_range = BMI_range;
                this.pref_workout = pref_workout;
        }

        public String getEmail() {
                return email;
        }
        public String getUseridentity() {
                return useridentity;
        }

        public int getAge() {
                return age;
        }

        public int getWeight() {
                return weight;
        }

        public int getHeight() {
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
