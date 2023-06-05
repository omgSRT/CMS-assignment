package com.springframework.assignmentCMS.controllers;

import com.springframework.assignmentCMS.models.Member;
import com.springframework.assignmentCMS.services.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String something() {
        return "index";
    }

    @GetMapping("/loginmember")
    public String loginPage() {
        return "login";
    }

    //    @PostMapping("/loginmember")
//    public String processLogin(HttpServletRequest request, @RequestParam String email, String password) {
//        HttpSession session = request.getSession();
//
//
//        Member member = memberService.findByEmailAndPassword(email, password);
//        if (member == null)
//            return "error";
//
//        session.setAttribute("member", member);
//        //model.addAttribute("member", member);
//        return "editprofile";
//    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/editprofile")
    public String editProfilePage(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberService.findByEmail(email);
        model.addAttribute("member", member);
        return "editprofile";
    }

    @PostMapping("/editprofile")
    public String editProfile(Model model,
                              @RequestParam String firstName, String lastName, String email,
                              String phone, String description) {
        Member m = memberService.findByEmail(email);
        m.setInfo(firstName, lastName, phone, description);
        model.addAttribute("notification", "Changed Successfully");
        memberService.saveMember(m);
        model.addAttribute("member", m);
        return "editprofile";
    }

    @GetMapping("/logoutmember")
    public String logout() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String regisMember(Model model,
                              @RequestParam String email, String username, String password,
                              String firstName, String lastName) {
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        Member member = new Member(firstName, lastName, email, username, encodedPassword);
        Member m = memberService.findByEmail(email);
        if (m != null) {
            return "error";
        }
        memberService.saveMember(member);
        model.addAttribute("notification", "Registered Successfully");
        return "login";
    }
}
