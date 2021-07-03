package com.bs23test.socialmediademo.service;

import com.bs23test.socialmediademo.model.Location;
import com.bs23test.socialmediademo.repository.LocationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getALlLocation() {
        try {
            return locationRepository.findAll();
        } catch (Exception e) {
            log.error("Error occurred in fetching list: "+e.getMessage());
            return null;
        }
    }
}
