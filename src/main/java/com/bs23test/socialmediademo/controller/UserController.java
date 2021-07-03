package com.bs23test.socialmediademo.controller;

import com.bs23test.socialmediademo.dto.UserDto;

import com.bs23test.socialmediademo.model.User;
import com.bs23test.socialmediademo.payload.ApiResponse;
import com.bs23test.socialmediademo.payload.ApiResponseError;
import com.bs23test.socialmediademo.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/users")
    public ResponseEntity<?> createUsers(@RequestBody UserDto userDto) {
        log.debug("REST request to create User : {}", userDto);
        if (userDto.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseError(false,400,"Failure","A new users cannot already have an ID"));

        }
        UserDto result = userService.create(userDto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseError(false,404,"Failed in Create User"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"Success",result));
    }


    @CrossOrigin
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        log.debug("REST request to update User : {}", userDto);
        if (userDto.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseError(false,400,"Failure","Can not Update non-existant User"));
        }
        UserDto result = userService.update(userDto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseError(false,400,"Failure","Found no User"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"Success",result));
    }

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<?> getAllUser() {
        List<User> all = userService.getAll();
        if (all.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"No Users in List",all));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"Fetched User list successfully",all));
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
        log.debug("REST request to get User by id : {}", id);
        if (id == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseError(false,400,"Id Not found"));
        }
        User user = userService.findByUserId(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"Found User Successfully",user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)  {
        log.debug("REST request to delete User by id : {}", id);
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseError(true,400,"Can not delete user without id"));
        }
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"User deleted successfully"));
    }


}
