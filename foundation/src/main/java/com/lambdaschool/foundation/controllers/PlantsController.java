package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.Plants;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.services.UserService;
import com.lambdaschool.foundation.services.PlantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * The entry point for client to access plants
 */
@RestController
@RequestMapping("/plants")
public class PlantsController
{
    /**
     * Using the plants service to process user, email combinations data
     */
    @Autowired
    PlantsService plantsService;

    @Autowired
    UserService userService;

    /**
     * List of all plants
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/plants",
        produces = "application/json")
    public ResponseEntity<?> listAllPlants()
    {
        List<Plants> allUserEmails = plantsService.findAll();
        return new ResponseEntity<>(allUserEmails,
            HttpStatus.OK);
    }

    /**
     * Return the plant based on ID
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/plant/{plantid}",
        produces = "application/json")
    public ResponseEntity<?> getPlantById(
        @PathVariable
            Long plantid)
    {
        Plants p = plantsService.findPlantsById(plantid);
        return new ResponseEntity<>(p,
            HttpStatus.OK);
    }

    /**
     * Removes the given plant based on id
     */
    @DeleteMapping(value = "/plant/{plantid}")
    public ResponseEntity<?> deletePlantById(
        @PathVariable
            long plantid)
    {
        plantsService.delete(plantid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Change the plant based of the request body and id given
     */
    @PutMapping("/plant/{plantid}")
    public ResponseEntity<?> updatePlant(
            @RequestBody
            Plants updatedplant,
        @PathVariable
            long plantid)
    {
        plantsService.update(updatedplant, plantid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Adds a new plant to user
     */
    @PostMapping(value = "/plant", consumes = "application/json")
    public ResponseEntity<?> addNewPlant(@Valid @RequestBody Plants newPlant)
            throws URISyntaxException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByName(authentication.getName());
        System.out.println("101:" + currentUser);
        newPlant.setPlantid(0);
        //currentUser.getPlants().add(newPlant);
        newPlant.setUser(currentUser);
        newPlant = plantsService.save(newPlant);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPlantURI = ServletUriComponentsBuilder.fromCurrentServletMapping()
            .path("/{plantid}")
            .buildAndExpand(newPlant.getPlantid())
            .toUri();
        responseHeaders.setLocation(newPlantURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }
}
