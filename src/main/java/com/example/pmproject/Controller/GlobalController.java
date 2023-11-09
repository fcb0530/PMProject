package com.example.pmproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {

    @GetMapping("/")
    public String main() {
        return "kakaoLoginSample";
    }

    @GetMapping("/index2")
    public String main2() {
        return "index2";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "member/register";
    }

}
