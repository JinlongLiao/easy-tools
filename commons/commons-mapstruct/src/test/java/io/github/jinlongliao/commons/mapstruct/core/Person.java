package io.github.jinlongliao.commons.mapstruct.core;

import io.github.jinlongliao.commons.mapstruct.annotation.Ignore;
import io.github.jinlongliao.commons.mapstruct.annotation.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Person extends Grep implements IAnimal {
    private static final Logger log = LoggerFactory.getLogger(Person.class);
    @Ignore
    private int ignore;
    private String name;
    private int age;
    @Mapping(method = "setBirth")
    private Date birthday;
    private List<String> arr;
    private Map<String, Object> arr2;
    public int[] array;

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

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

    public Map<String, Object> getArr2() {
        return arr2;
    }

    public void setArr2(Map<String, Object> arr2) {
        this.arr2 = arr2;
    }

    public List<String> getArr() {
        return arr;
    }

    public void setArr(List<String> arr) {
        this.arr = arr;
    }

    public int getIgnore() {
        return ignore;
    }

    public void setIgnore(int ignore) {
        this.ignore = ignore;
    }
}