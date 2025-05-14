package com.project.controller;

import com.project.services.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
public class JobOfferController {
    @Autowired
    private JobOfferService jobOfferService;

    @PostMapping("/{id}/job-notify")
    public ResponseEntity<Void> getJobOfferService(@PathVariable Long id,
                                                     @RequestParam String position) {
    jobOfferService.notify(id, position);
    return ResponseEntity.ok().build();

    }
}
