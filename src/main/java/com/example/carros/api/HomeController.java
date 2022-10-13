package com.example.carros.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(value = "HomeController")
@RestController
@RequestMapping("/home") // DEFINE PARA ONDE O WEB SERVICE ESTA MAPEADO
public class HomeController {

    @GetMapping
    public String ok(){
        return "ok";
    }

    @GetMapping("/parametro/{x}")
    public String ok(@RequestParam("x") String x){
        return x;
    }

    @PostMapping
    public String post(){
        return "ok";
    }

    @PutMapping
    public String put(){
        return "ok";
    }

    @DeleteMapping
    public String delete(){
        return "ok";
    }
}
