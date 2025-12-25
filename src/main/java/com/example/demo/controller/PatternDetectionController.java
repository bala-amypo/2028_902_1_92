package com.example.demo.controller;

import com.example.demo.model.PatternDetectionResult;
import com.example.demo.service.PatternDetectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patterns")
@Tag(name = "Pattern Detection")
public class PatternDetectionController {
    private final PatternDetectionService detectionService;
    
    public PatternDetectionController(PatternDetectionService detectionService) {
        this.detectionService = detectionService;
    }
    
    @PostMapping("/detect/{zoneId}")
    @Operation(summary = "Run pattern detection for zone")
    public ResponseEntity<PatternDetectionResult> detectPattern(@PathVariable Long zoneId) {
        PatternDetectionResult result = detectionService.detectPattern(zoneId);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Get pattern results for zone")
    public ResponseEntity<List<PatternDetectionResult>> getResultsByZone(@PathVariable Long zoneId) {
        List<PatternDetectionResult> results = detectionService.getResultsByZone(zoneId);
        return ResponseEntity.ok(results);
    }
}