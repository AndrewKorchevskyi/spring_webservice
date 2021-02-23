package com.simplewebservice.etc.booksapi;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

@JsonFilter("BookFilter")
public class Book {

    int id;

    String name;

    String author;

    @Nullable
    @JsonIgnore
    String pagesNumber;

    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Book setPagesNumber(@Nullable String pagesNumber) {
        this.pagesNumber = pagesNumber;
        return this;
    }

    public String getPagesNumber() {
        return pagesNumber;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
