package com.courses.hexagonalapi.internship.application;

import com.courses.hexagonalapi.internship.domain.defenses.Defense;
import com.courses.hexagonalapi.internship.domain.defenses.SubmitDefenseUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/defenses")
public class DefenseController {

    public SubmitDefenseUseCase submitDefenseUseCase;

    public DefenseController(SubmitDefenseUseCase submitDefenseUseCase) {
        this.submitDefenseUseCase = submitDefenseUseCase;
    }
    @PostMapping
    public ResponseEntity<Defense> createDefense(@RequestBody DefenseRequest request) {
        Defense defense = submitDefenseUseCase.saveDefense(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(defense);
    }
}
