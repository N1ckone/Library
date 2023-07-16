package ru.nikon.models;

import javax.validation.constraints.*;


public class Book {
    private int id;

    @NotEmpty(message = "Введите название")
    private String name;

    private String author;
    @Min(value = 0, message = "Год написания должен быть положительным числом")
    @Max(value = 2023, message = "Год написания не может быть больше 2023")
    private int year;

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
