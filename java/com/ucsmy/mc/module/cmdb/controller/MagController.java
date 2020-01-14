package com.ucsmy.mc.module.cmdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class MagController {
    @RequestMapping
    public String index() {
        return "/common/userlogin/home";
    }
}
