package com.rafaelcavalcante.blog.service;

import java.util.List;

import com.rafaelcavalcante.blog.model.Post;

public interface PostService {

    List<Post> findAll();

    Post findById(Long id);

    Post save(Post post);
}
