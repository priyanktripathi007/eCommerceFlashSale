package com.pramati.kart.loadBalancer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class GatewayController {
    @RequestMapping("/login")
    public String login() {
        return "forward:/";
    }
    @RequestMapping("/")
    public String index() {
        return "login";
    }
}
