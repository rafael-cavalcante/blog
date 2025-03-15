package com.rafaelcavalcante.blog.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rafaelcavalcante.blog.model.Post;
import com.rafaelcavalcante.blog.service.PostService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ModelAndView listarPots() {
        ModelAndView modelAndView = new ModelAndView("posts");
        modelAndView.addObject("posts", this.postService.findAll());
        return modelAndView;
    }

    @GetMapping("/posts/{id}")
    public ModelAndView buscarPost(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("visualizarPost");
        Post post = this.postService.findById(id);
        modelAndView.addObject("post", post);
        return modelAndView;
    }

    @GetMapping("/posts/newpost")
    public ModelAndView getPostForm() {
        ModelAndView modelAndView = new ModelAndView("postForm");
        modelAndView.addObject("post", new Post());
        return modelAndView;
    }

    @PostMapping("/posts/newpost")
    public String savePostForm(@ModelAttribute @Valid Post post, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
            return "redirect:/posts/newpost";
        }

        post.setDate(LocalDate.now());
        this.postService.save(post);

        return "redirect:/posts";
    }

}
