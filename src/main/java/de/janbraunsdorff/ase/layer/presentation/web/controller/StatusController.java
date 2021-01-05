package de.janbraunsdorff.ase.layer.presentation.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public String status(){
        return "{\"status\": \"läuft alls gut bis jetzt noch\"}";
    }
}
