
package com.example.demo.controller;

import com.example.demo.model.AnalysisLog;
import com.example.demo.service.AnalysisLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@Tag(name = "Analysis Logs")
@SecurityRequirement(name = "bearerAuth")
public class AnalysisLogController {

private final AnalysisLogService analysisLogService;

public AnalysisLogController(AnalysisLogService analysisLogService) {
this.analysisLogService = analysisLogService;
}

@PostMapping("/{zoneId}")
@Operation(summary = "Add analysis log for zone")
public ResponseEntity<AnalysisLog> addLog(@PathVariable Long zoneId,
@RequestBody String message) {
AnalysisLog log = analysisLogService.addLog(zoneId, message);
return ResponseEntity.ok(log);
}

@GetMapping("/zone/{zoneId}")
@Operation(summary = "Fetch logs for zone")
public ResponseEntity<List<AnalysisLog>> getLogsByZone(@PathVariable Long zoneId) {
return ResponseEntity.ok(analysisLogService.getLogsByZone(zoneId));
}
}
