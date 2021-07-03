package com.bs23test.socialmediademo.repository;

import com.bs23test.socialmediademo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
