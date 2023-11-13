package com.example.pmproject.Controller;

import com.example.pmproject.DTO.MemberDTO;
import com.example.pmproject.Service.GlobalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class GlobalController {

    private final GlobalService globalService;

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/member/register")
    public String registerForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/register";
    }

    @PostMapping("/member/register")
    public String register(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("memberDTO",memberDTO);
            return "member/register";
        }
        globalService.register(memberDTO);
        return "redirect:/";
    }
}
