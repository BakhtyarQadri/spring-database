package com.example.jdbcpostgresql;

import com.example.jdbcpostgresql.AlienContract.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/aliens")
public class AlienController {

    @PostMapping
    public void addAlien(
            @RequestBody AddAlienReq reqBody
    ) {
        System.out.println(reqBody);
    }

    @GetMapping
    public void getAliens() {
        System.out.println("Get Aliens");
    }

    @PutMapping("/{alienCnic}")
    public void updateAlien(
            @PathVariable String alienCnic,
            @RequestBody UpdateAlienReq reqBody
    ) {
        System.out.println(reqBody);
    }

    @DeleteMapping
    public void deleteAliens() {
        System.out.println("Delete Aliens");
    }

    // @PatchMapping

}
