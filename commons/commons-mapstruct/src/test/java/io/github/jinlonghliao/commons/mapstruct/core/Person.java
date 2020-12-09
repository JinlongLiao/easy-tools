package io.github.jinlonghliao.commons.mapstruct.core;

import io.github.jinlonghliao.commons.mapstruct.annotation.Mapping;

import java.util.Date;

public class Person {
    private String name;
    private int age;
    @Mapping(method = "setBirth")
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirth(Date birthday) {
        this.birthday = birthday;
    }
}