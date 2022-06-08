package com.example.sqlsecurity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Person {
    BigDecimal person_id;
    String name;
    String gender;
    Timestamp birthday;

    public Person(BigDecimal person_id, String name, String gender, Timestamp birthday) {
        this.person_id = person_id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
    }

    public BigDecimal getPerson_id() {
        return person_id;
    }

    public void setPerson_id(BigDecimal person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }
}
