package com.rafaelcavalcante.blog.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafaelcavalcante.blog.model.Post;
import com.rafaelcavalcante.blog.repository.PostRepository;

//import jakarta.annotation.PostConstruct;

@Component
public class DummyData {

    @Autowired
    private PostRepository postRepository;

    // @PostConstruct
    public void savePosts() {
        List<Post> posts = new ArrayList<>();

        Post post1 = new Post();
        post1.setAuthor("Rafael Cavalcante");
        post1.setDate(LocalDate.now());
        post1.setTitle("Spring Boot");
        post1.setContent(
                "Spring Boot is a project built on the top of the Spring Framework. It provides a simpler and faster way to set up, configure, and run both simple and web-based applications.");
        posts.add(post1);

        Post post2 = new Post();
        post2.setAuthor("Junior Confessor");
        post2.setDate(LocalDate.now());
        post2.setTitle("Java");
        post2.setContent(
                "Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.");
        posts.add(post2);

        for (Post post : posts) {
            Post postSaved = postRepository.save(post);
            System.out.println(postSaved.getId());
        }
    }
}
