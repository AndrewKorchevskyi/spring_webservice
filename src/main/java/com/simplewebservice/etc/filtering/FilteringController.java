package com.simplewebservice.etc.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.simplewebservice.etc.booksapi.Book;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping(path = "/filter-book-static")
    public Book retrieveFilteredBookStatic() {
        return new Book(1, "Harry Potter", "Joanne Rowling").setPagesNumber("900");
    }

    @GetMapping(path = "/filter-book-dynamic")
    public MappingJacksonValue retrieveFilteredBookDynamic() {
        Book book = new Book(1, "Harry Potter", "Joanne Rowling").setPagesNumber("900");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("BookFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(book);
        mapping.setFilters(filterProvider);

        return mapping;
    }
}
