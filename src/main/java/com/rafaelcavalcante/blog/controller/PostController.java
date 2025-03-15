package com.rafaelcavalcante.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.rafaelcavalcante.blog.model.Post;
import com.rafaelcavalcante.blog.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
