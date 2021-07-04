package com.bs23test.socialmediademo.controller;

import com.bs23test.socialmediademo.dto.PostDto;
import com.bs23test.socialmediademo.model.Post;
import com.bs23test.socialmediademo.payload.ApiResponse;
import com.bs23test.socialmediademo.payload.ApiResponseError;
import com.bs23test.socialmediademo.payload.PostRequest;
import com.bs23test.socialmediademo.security.CurrentUser;
import com.bs23test.socialmediademo.security.UserPrincipal;
import com.bs23test.socialmediademo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @CrossOrigin
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest, @CurrentUser UserPrincipal userPrincipal) {
        if (postRequest == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseError(false,400,"Failure","Invalid Request"));
        }
        PostDto post = postService.createPost(postRequest, userPrincipal);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseError(false,404,"Failure","Not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"Success",post));
    }

    @CrossOrigin
    @PutMapping("/post")
    public ResponseEntity<?> updatePost(@RequestBody PostDto postDto, @CurrentUser UserPrincipal userPrincipal) {
        if (postDto == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseError(false,400,"Failure","Invalid Request"));
        }
        PostDto post = postService.updatePost(postDto, userPrincipal);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseError(false,404,"Failure","Not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"Success",post));
    }

    @CrossOrigin
    @GetMapping("/post/all-private")
    public ResponseEntity<?> viewPrivatePosts(@CurrentUser UserPrincipal userPrincipal) {
        List<Post> posts = postService.getAllPostsByUserId(userPrincipal.getId());
        if (posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseError(true,404,"Failure","No Post found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"Success",posts));
    }

    @CrossOrigin
    @GetMapping("/post")
    public ResponseEntity<?> viewAllPosts() {
        List<Post> posts = postService.getPosts();
        if (posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseError(true,404,"Failure","No Post found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"Success",posts));
    }

}
