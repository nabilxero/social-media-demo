package com.bs23test.socialmediademo.controller;

import com.bs23test.socialmediademo.config.JwtConfig;
import com.bs23test.socialmediademo.payload.ApiResponseError;
import com.bs23test.socialmediademo.payload.AuthCredentialError;
import com.bs23test.socialmediademo.payload.AuthResponse;
import com.bs23test.socialmediademo.payload.LoginRequest;
import com.bs23test.socialmediademo.security.JwtTokenProvider;
import com.bs23test.socialmediademo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping("/issueToken")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) throws IOException {
//        if (bindingResult.hasErrors()) {
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            List<AuthCredentialError> message = new ArrayList<>();
//            for (FieldError e : errors) {
//                AuthCredentialError authCredentialError = new AuthCredentialError();
//                authCredentialError.setField(e.getField());
//                authCredentialError.setMessage(e.getDefaultMessage());
//                message.add(authCredentialError);
//            }
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseError(false, 400, "Invalid Parameter.", message));
//        } else {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = jwtTokenProvider.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponse(accessToken, (long) jwtConfig.getExpiration() * 1000, "Bearer"), HttpStatus.OK);
//        }


    }

}
