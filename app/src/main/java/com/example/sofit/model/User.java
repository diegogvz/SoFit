package com.example.sofit.model;

public class User {
    private String name;
    private String sex;
    private int age;
    private int weight;
    private int height;
    private String image;

    public User(){

    }

    public User(String name, String sex, int age, int weight, int height) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }
    public User(String name, String sex, int age, int weight, int height, String image) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String isSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImage(String image){ this.image = image; }

    public String getImage(){ return this.image; }
}
