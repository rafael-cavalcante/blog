package com.rafaelcavalcante.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.rafaelcavalcante.blog.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ModelAndView listarPots() {
        ModelAndView modelAndView = new ModelAndView("posts");
        modelAndView.addObject("posts", postService.findAll());
        return modelAndView;
    }
}
