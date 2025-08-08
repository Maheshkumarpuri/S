package com.example.tfms.controller;

import com.example.tfms.model.entity.LetterOfCredit;
import com.example.tfms.model.entity.enums.LetterOfCreditStatus;
import com.example.tfms.service.LetterOfCreditService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/lc")
public class LetterOfCreditController {

    private final LetterOfCreditService service;

    public LetterOfCreditController(LetterOfCreditService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        List<LetterOfCredit> letters = service.listAll();
        model.addAttribute("letters", letters);
        model.addAttribute("title", "Letters of Credit");
        return "lc/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("letterOfCredit", new LetterOfCredit());
        model.addAttribute("title", "Create Letter of Credit");
        model.addAttribute("currencies", List.of("USD", "EUR", "GBP", "JPY", "CNY"));
        return "lc/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute LetterOfCredit letterOfCredit,
                        BindingResult bindingResult,
                        Model model,
                        RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Create Letter of Credit");
            model.addAttribute("currencies", List.of("USD", "EUR", "GBP", "JPY", "CNY"));
            return "lc/form";
        }

        try {
            LetterOfCredit saved = service.create(letterOfCredit);
            redirectAttributes.addFlashAttribute("message", 
                "Letter of Credit #" + saved.getLcId() + " created successfully!");
            return "redirect:/lc";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create Letter of Credit: " + e.getMessage());
            model.addAttribute("title", "Create Letter of Credit");
            model.addAttribute("currencies", List.of("USD", "EUR", "GBP", "JPY", "CNY"));
            return "lc/form";
        }
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            LetterOfCredit lc = service.getById(id);
            model.addAttribute("letterOfCredit", lc);
            model.addAttribute("title", "Letter of Credit #" + id);
            return "lc/view";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Letter of Credit not found");
            return "redirect:/lc";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            LetterOfCredit lc = service.getById(id);
            model.addAttribute("letterOfCredit", lc);
            model.addAttribute("title", "Edit Letter of Credit #" + id);
            model.addAttribute("currencies", List.of("USD", "EUR", "GBP", "JPY", "CNY"));
            model.addAttribute("statuses", LetterOfCreditStatus.values());
            return "lc/edit";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Letter of Credit not found");
            return "redirect:/lc";
        }
    }

    @PostMapping("/{id}")
    public String amend(@PathVariable Long id,
                       @Valid @ModelAttribute LetterOfCredit letterOfCredit,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Edit Letter of Credit #" + id);
            model.addAttribute("currencies", List.of("USD", "EUR", "GBP", "JPY", "CNY"));
            model.addAttribute("statuses", LetterOfCreditStatus.values());
            return "lc/edit";
        }

        try {
            LetterOfCredit amended = service.amend(id, letterOfCredit);
            redirectAttributes.addFlashAttribute("message", 
                "Letter of Credit #" + amended.getLcId() + " amended successfully!");
            return "redirect:/lc/" + id;
        } catch (Exception e) {
            model.addAttribute("error", "Failed to amend Letter of Credit: " + e.getMessage());
            model.addAttribute("title", "Edit Letter of Credit #" + id);
            model.addAttribute("currencies", List.of("USD", "EUR", "GBP", "JPY", "CNY"));
            model.addAttribute("statuses", LetterOfCreditStatus.values());
            return "lc/edit";
        }
    }

    @PostMapping("/{id}/close")
    public String close(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            LetterOfCredit closed = service.close(id);
            redirectAttributes.addFlashAttribute("message", 
                "Letter of Credit #" + closed.getLcId() + " closed successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Failed to close Letter of Credit: " + e.getMessage());
        }
        return "redirect:/lc/" + id;
    }
}