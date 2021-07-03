package com.bs23test.socialmediademo.controller;

import com.bs23test.socialmediademo.model.Location;
import com.bs23test.socialmediademo.payload.ApiResponse;
import com.bs23test.socialmediademo.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/location")
    public ResponseEntity<?> getAllLocation() {
        List<Location> locationList = locationService.getALlLocation();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,200,"Success",locationList));
    }
}
