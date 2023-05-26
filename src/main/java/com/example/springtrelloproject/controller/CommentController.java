package com.example.springtrelloproject.controller;

import com.example.springtrelloproject.dto.CommentCreateEditDto;
import com.example.springtrelloproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("comments", commentService.findAll());
        return "";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("comment", commentService.findById(id));
        return "";
    }

    @PostMapping("/create")
    public String create(CommentCreateEditDto comment) {
        commentService.create(comment);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         CommentCreateEditDto comment) {
        commentService.update(id, comment);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        commentService.delete(id);
        return "redirect:/";
    }
}
