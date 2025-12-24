
package com.example.demo.controller;

import com.example.demo.model.PatternDetectionResult;
import com.example.demo.service.PatternDetectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patterns")
@Tag(name = "Pattern Detection")
@SecurityRequirement(name = "bearerAuth")
public class PatternDetectionController {

private final PatternDetectionService patternDetectionService;

public PatternDetectionController(PatternDetectionService patternDetectionService) {
this.patternDetectionService = patternDetectionService;
}

@PostMapping("/detect/{zoneId}")
@Operation(summary = "Run detection for zone")
public ResponseEntity<PatternDetectionResult> detectForZone(@PathVariable Long zoneId) {
PatternDetectionResult result = patternDetectionService.detectPattern(zoneId);
return ResponseEntity.ok(result);
}

@GetMapping("/zone/{zoneId}")
@Operation(summary = "Fetch detection results for zone")
public ResponseEntity<List<PatternDetectionResult>> getResultsByZone(@PathVariable Long zoneId) {
return ResponseEntity.ok(patternDetectionService.getResultsByZone(zoneId));
}
}