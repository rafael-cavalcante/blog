package com.rafaelcavalcante.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelcavalcante.blog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
