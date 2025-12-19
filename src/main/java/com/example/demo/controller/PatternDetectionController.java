
package com.example.demo.controller;

import com.example.demo.model.PatternDetectionResult;
import com.example.demo.service.PatternDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patterns")
public class PatternDetectionController {

private final PatternDetectionService patternDetectionService;

public PatternDetectionController(PatternDetectionService patternDetectionService) {
this.patternDetectionService = patternDetectionService;
}

// POST /patterns/detect/{zoneId} – run detection for zone
@PostMapping("/detect/{zoneId}")
public ResponseEntity<PatternDetectionResult> detect(@PathVariable("zoneId") Long zoneId) {
PatternDetectionResult result = patternDetectionService.detectPattern(zoneId);
return ResponseEntity.ok(result);
}

// GET /patterns/zone/{zoneId} – fetch detection results
@GetMapping("/zone/{zoneId}")
public ResponseEntity<List<PatternDetectionResult>> getResults(@PathVariable("zoneId") Long zoneId) {
return ResponseEntity.ok(patternDetectionService.getResultsByZone(zoneId));
}
}
