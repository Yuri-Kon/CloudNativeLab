package com.hit.order.controller;

import com.hit.order.feign.MovieClient;
import com.hit.order.feign.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final UserClient userClient;
    private final MovieClient movieClient;

    public OrderController(UserClient userClient, MovieClient movieClient) {
        this.userClient = userClient;
        this.movieClient = movieClient;
    }

    @GetMapping("/orders")
    public String getOrders() {
        String user = userClient.getHello();
        List<String> movies = movieClient.getMovieList();
        return "Order created by [" + user + "], movie list: " + movies;
    }
}
