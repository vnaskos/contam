package com.bugbusters.contam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "x", required = false, defaultValue = "0") String x,
                        @RequestParam(value = "y", required = false, defaultValue = "0") String y) {
        model.addAttribute("latitude", x);
        model.addAttribute("longitude", y);
        return "index";
    }

}
