package com.hit.order.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")  // 服务名注册到Eureka
public interface UserClient {

    @GetMapping("/hello")
    String getHello();
}
