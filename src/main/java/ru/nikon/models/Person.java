package ru.nikon.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Person {

    private int id;
    @NotEmpty(message = "Введите имя и фамилию")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+", message = "Полное имя должно состоять из имени и фамиилии, которые начинаются с большой буквой и разделяются пробелом")
    private String name;
    @Min(value = 1900, message = "Год рождения должен быть не меньше 1900")
    @Max(value = 2023, message = "Год рождения не может быть больше 2023")
    private int birthYear;

    public Person(int id, String name, int birthYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }

    public Person() {}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
