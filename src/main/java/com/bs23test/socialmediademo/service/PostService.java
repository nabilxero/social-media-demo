package com.bs23test.socialmediademo.service;

import com.bs23test.socialmediademo.dto.PostDto;
import com.bs23test.socialmediademo.dto.UserDto;
import com.bs23test.socialmediademo.mapper.PostMapper;
import com.bs23test.socialmediademo.model.Post;
import com.bs23test.socialmediademo.model.PrivacyType;
import com.bs23test.socialmediademo.payload.PostRequest;
import com.bs23test.socialmediademo.repository.PostRepository;
import com.bs23test.socialmediademo.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostDto createPost(PostRequest postRequest, UserPrincipal userPrincipal) {

        try {
            Post post = new Post();
            post.setUserId(userPrincipal.getId());
            post.setStatus(postRequest.getPostStatus());
            post.setUserId(userPrincipal.getId());
            post.setPrivacyType(postRequest.getPrivacyType());
            post.setLocationId(Long.valueOf(postRequest.getLocationId()));
            postRepository.save(post);
            return postMapper.toDto(post);
        } catch (Exception e) {
            log.error("Error occurred in Saving Post: " + e.getMessage());
            return null;
        }

    }

    public PostDto updatePost(PostDto postDto, UserPrincipal userPrincipal) {
        try {
            log.info("Request to Update a Post: "+ postDto);
            Post post = postRepository.findById(Long.valueOf(postDto.getId())).get();
            post.setStatus(postDto.getStatus());
            post.setUserId(userPrincipal.getId());
            post.setLocationId(postDto.getLocationId());
            post.setPrivacyType(postDto.getPrivacyType());
            Post updatedPost = postRepository.save(post);
            return postMapper.toDto(updatedPost);
        } catch (Exception e) {
            log.error("Error occurred in updating Post: "+e.getMessage());
            return null;
        }
    }


    public List<Post> getPosts() {
        try {
            log.debug("Getting all Post");
            List<Post> all = postRepository.findAll();
            return all
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(p -> p.getPrivacyType().equals(PrivacyType.PUBLIC))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Error in fetching list: " + e.getMessage());
            return null;
        }
    }

    public List<Post> getAllPostsByUserId(Long id) {
        try {
            log.debug("Getting all Post");
            return postRepository.findAllByUserId(id);
        } catch (Exception e) {
            log.error("Error in fetching list: " + e.getMessage());
            return null;
        }
    }

}
