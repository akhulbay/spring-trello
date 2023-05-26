package com.example.springtrelloproject.controller;

import com.example.springtrelloproject.dto.TaskCategoryCreateEditDto;
import com.example.springtrelloproject.service.TaskCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class TaskCategoryController {

    private final TaskCategoryService taskCategoryService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("categories", taskCategoryService.findAll());
        return "";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("category", taskCategoryService.findById(id));
        return "";
    }

    @PostMapping("/create")
    public String create(TaskCategoryCreateEditDto taskCategory) {
        taskCategoryService.create(taskCategory);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         TaskCategoryCreateEditDto taskCategory) {
        taskCategoryService.update(id, taskCategory);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        taskCategoryService.delete(id);
        return "redirect:/";
    }
}
