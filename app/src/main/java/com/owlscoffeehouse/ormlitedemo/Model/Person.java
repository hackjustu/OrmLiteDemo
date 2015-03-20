package com.owlscoffeehouse.ormlitedemo.Model;

import com.j256.ormlite.field.DatabaseField;

public class Person {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;

    public Person() {
    }

    public Person(String name) {

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstname) {
        this.name = firstname;
    }
}
