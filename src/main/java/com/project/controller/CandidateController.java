package com.project.controller;


import com.project.dto.BankDTO;
import com.project.dto.CandidateDTO;
import com.project.dto.EducationDTO;
import com.project.dto.PersonalDTO;
import com.project.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService cs;

    //create candidate
    @PostMapping("/add")
    public ResponseEntity<CandidateDTO> addCandidate(@Valid @RequestBody CandidateDTO dto) {
        CandidateDTO candidate=cs.addUser(dto);
        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }

    //update candidate
    @PutMapping("/{id}")
    public ResponseEntity<CandidateDTO> updateCandidate(@Valid @PathVariable Long id, @Valid @RequestBody CandidateDTO dto) {
        CandidateDTO updated = cs.updateUser(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //adding personal details
    @PostMapping("/personal/{id}")
    public ResponseEntity<Void> addPersonalInfo(@Valid @PathVariable Long id, @RequestBody PersonalDTO dto) {
        cs.addPersonalInfo(id,dto);
        return ResponseEntity.ok().build();

    }

    //adding bank details
    @PostMapping("/bank/{id}")
    public ResponseEntity<Void> addBankInfo(@Valid @PathVariable Long id, @RequestBody BankDTO dto) {
        cs.addBankInfo(id,dto);
        return ResponseEntity.ok().build();
    }

    //adding education details
    @PostMapping("/edu/{id}")
    public ResponseEntity<Void>addEducationInfo(@Valid @PathVariable Long id, @RequestBody EducationDTO dto) {
        cs.addEducationInfo(id,dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody CandidateDTO dto) {
        cs.updateStatus(id,dto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/onboardingstatus/{id}")
    public ResponseEntity<Void> updateOnboardingStatus(@PathVariable Long id, @RequestBody String  onboardStatus) {
        cs.onboardingStatus(id,onboardStatus);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCandidateCount() {
        return ResponseEntity.ok(cs.getCandidateCount());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<CandidateDTO>view(@PathVariable Long id) {
        return ResponseEntity.ok(cs.viewCandidate(id));
    }



}
