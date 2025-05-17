package com.hit.movie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @GetMapping("/movies")
    public List<String> getMovies() {
        return List.of("Interstellar", "Inception", "The Dark Knight");
    }

}
