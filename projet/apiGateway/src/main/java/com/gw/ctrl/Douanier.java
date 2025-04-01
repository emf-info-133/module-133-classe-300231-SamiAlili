package com.gw.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gw")
public class Douanier {

    // Handler pour GET
    @GetMapping("/")
    public String getNothing() {
        return "";
    }

}
