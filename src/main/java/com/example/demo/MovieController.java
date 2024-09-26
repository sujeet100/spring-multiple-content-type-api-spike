package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @GetMapping(value = "/movies", produces = {"application/json", "text/csv"})
    public Movies getMovies() {
        return new Movies(List.of(
                new Movie("The Shawshank Redemption", "Frank Darabont", 1994),
                new Movie("The Godfather", "Francis Ford Coppola", 1972),
                new Movie("The Dark Knight", "Christopher Nolan", 2008),
                new Movie("The Lord of the Rings: The Return of the King", "Peter Jackson", 2003),
                new Movie("Pulp Fiction", "Quentin Tarantino", 1994)
        ));
    }
}
