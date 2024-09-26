package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvRecurse;

import java.util.List;

public class Movies implements CSVSerializable {
    @CsvRecurse
    private List<Movie> movies;

    public Movies(List<Movie> movies) {
        this.movies = movies;
    }

    public Movies() {
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    @JsonIgnore
    public List<CSVBean> toCSV() {
        return List.copyOf(movies);
    }
}
