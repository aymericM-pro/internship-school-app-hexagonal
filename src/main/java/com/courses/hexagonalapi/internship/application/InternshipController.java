package com.courses.hexagonalapi.internship.application;

import com.courses.hexagonalapi.internship.domain.FindAllInternshipUseCase;
import com.courses.hexagonalapi.internship.domain.FindInternshipByIdUseCase;
import com.courses.hexagonalapi.internship.domain.Internship;
import com.courses.hexagonalapi.internship.domain.SubmitInternshipUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/internships")
public class InternshipController {

    private final FindInternshipByIdUseCase findInternshipByIdUseCase;
    private final SubmitInternshipUseCase submitInternshipUseCase;
    private final FindAllInternshipUseCase findAllInternshipUseCase;

    public InternshipController(
            FindInternshipByIdUseCase findInternshipByIdUseCase,
            SubmitInternshipUseCase submitInternshipUseCase,
            FindAllInternshipUseCase findAllInternshipUseCase
    ) {
        this.findInternshipByIdUseCase = findInternshipByIdUseCase;
        this.submitInternshipUseCase = submitInternshipUseCase;
        this.findAllInternshipUseCase = findAllInternshipUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Internship> getInternshipById(@PathVariable UUID id) {
        Internship internship = findInternshipByIdUseCase.findById(id);
        return ResponseEntity.ok(internship);
    }

    @GetMapping
    public ResponseEntity<List<Internship>> findAllInternship() {
        List<Internship> internships = findAllInternshipUseCase.findAll();
        return ResponseEntity.ok(internships);
    }

    @PostMapping
    public ResponseEntity<Internship> submitInternship(@RequestBody SubmitInternshipRequest request) {
        Internship internship = submitInternshipUseCase.save(request);
        return ResponseEntity
                .status(201)
                .body(internship);
    }
}
