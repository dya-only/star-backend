package kro.kr.gbsw_star.domain.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api")
public class AppController {

    @GetMapping
    public static String getHello() {
        return "Hello, World!";
    }
}