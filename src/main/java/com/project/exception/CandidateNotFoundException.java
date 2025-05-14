package com.project.exception;

public class CandidateNotFoundException extends RuntimeException {

    private final Long id;

    public CandidateNotFoundException(Long id) {

        super("Candidate with this id: " + id + " not found");
        this.id = id;
    }
    public Long getId(){
        return id;
    }
}
