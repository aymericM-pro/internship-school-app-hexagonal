package com.courses.hexagonalapi.internship.application;

import com.courses.hexagonalapi.internship.domain.defenses.Defense;
import com.courses.hexagonalapi.internship.domain.defenses.FindAllDefenseUseCase;
import com.courses.hexagonalapi.internship.domain.defenses.FindByIdDefenseUseCase;
import com.courses.hexagonalapi.internship.domain.defenses.SubmitDefenseUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/defenses")
public class DefenseController {

    public SubmitDefenseUseCase submitDefenseUseCase;
    public FindAllDefenseUseCase findAllDefenseUseCase;
    public FindByIdDefenseUseCase findByIdDefenseUseCase;

    public DefenseController(
            SubmitDefenseUseCase submitDefenseUseCase,
            FindAllDefenseUseCase findAllDefenseUseCase,
            FindByIdDefenseUseCase findByIdDefenseUseCase) {
        this.submitDefenseUseCase = submitDefenseUseCase;
        this.findAllDefenseUseCase = findAllDefenseUseCase;
        this.findByIdDefenseUseCase = findByIdDefenseUseCase;
    }

    @PostMapping
    public ResponseEntity<Defense> createDefense(@RequestBody DefenseRequest request) {
        Defense defense = submitDefenseUseCase.saveDefense(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(defense);
    }

    @GetMapping
    public ResponseEntity<List<Defense>> findAllDefense() {
        List<Defense> defenses = findAllDefenseUseCase.findAllDefenses();
        return ResponseEntity.status(HttpStatus.OK).body(defenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Defense> findDefenseById(@PathVariable("id") UUID defenseId) {
        Defense defense = findByIdDefenseUseCase.findDefenseById(defenseId);
        return ResponseEntity.ok(defense);
    }
}
