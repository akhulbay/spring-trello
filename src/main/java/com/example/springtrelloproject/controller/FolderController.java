package com.example.springtrelloproject.controller;

import com.example.springtrelloproject.dto.FolderCreateEditDto;
import com.example.springtrelloproject.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/folders")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("folders", folderService.findAll());
        return "";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("folder", folderService.findById(id));
        return "";
    }

    @PostMapping("/create")
    public String create(FolderCreateEditDto folder) {
        folderService.create(folder);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         FolderCreateEditDto folder) {
        folderService.update(id, folder);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        folderService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/assign-category")
    public String assignCategory(@RequestParam("folder-id") Long folderId,
                                 @RequestParam("category-id") Long categoryId) {
        folderService.assignCategory(folderId, categoryId);
        return "redirect:/";
    }

    @PostMapping("/unassign-category")
    public String unassignCategory(@RequestParam("folder-id") Long folderId,
                                   @RequestParam("category-id") Long categoryId) {
        folderService.unassignCategory(folderId, categoryId);
        return "redirect:/";
    }
}
