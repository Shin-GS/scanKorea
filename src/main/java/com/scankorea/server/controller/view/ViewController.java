package com.scankorea.server.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping({"/", "/scan"})
    public String scanPage() {
        return "/views/scan/scan";
    }
}
