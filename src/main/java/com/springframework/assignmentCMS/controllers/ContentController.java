package com.springframework.assignmentCMS.controllers;

import com.springframework.assignmentCMS.models.Content;
import com.springframework.assignmentCMS.models.Member;
import com.springframework.assignmentCMS.services.ContentService;
import com.springframework.assignmentCMS.services.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/contentlist")
    public String contentListPage(Model model) {
        model.addAttribute("contents", contentService.getAllContent());
        return "contentlist";
    }

    @GetMapping("/addcontent")
    public String addContentPage() {
        return "addcontent";
    }

    @PostMapping("/addcontent")
    public String addContent(Model model,
                             @RequestParam String title, String brief, String content) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Member m = memberService.findByEmail(email);
//        Member m = (Member) model.getAttribute("member");
        Content c = new Content(m.getMemberId(), title, brief, content);
        model.addAttribute("notification", "Added Successfully");
        contentService.saveContent(c);
        return "addcontent";
    }
}

