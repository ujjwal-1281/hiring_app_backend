package com.project.controller;

import com.project.dto.DocumentDTO;
import com.project.services.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.Document;


@RestController
@RequiredArgsConstructor
@RequestMapping("/candidates")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping(value="/{id}/upload-docs", consumes = {"multipart/form-data"})
    public ResponseEntity<Void> uploadDocument(@Valid @PathVariable Long id,
                                               @RequestPart("file") MultipartFile file,
                                               @RequestParam("candidateId") Long candidateId,
                                               @RequestParam("fileType") String fileType) {

        System.out.println("Received file: " + file.getOriginalFilename());

        DocumentDTO data = new DocumentDTO(candidateId, fileType);

        System.out.println("Received data: " + data);

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

            documentService.uploadDoc(id, file,data);
            return ResponseEntity.ok().build();

    }
    @PutMapping("/{id}/verify-docs")
    public ResponseEntity<Void> verifyDocument(@PathVariable Long id,
                                               @RequestParam Long documentID) {
        documentService.verified(id, documentID);
        return ResponseEntity.ok().build();
    }


}
