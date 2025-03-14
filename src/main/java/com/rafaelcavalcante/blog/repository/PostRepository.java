package com.rafaelcavalcante.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelcavalcante.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
