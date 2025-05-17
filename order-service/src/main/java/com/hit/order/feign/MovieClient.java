package com.hit.order.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "movie-service")
public interface MovieClient {

    @GetMapping("/movies")
    List<String> getMovieList();  // 后期可替换为 List<MovieDto>
}