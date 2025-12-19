AnalysisLogController

package com.example.demo.controller;

import com.example.demo.model.AnalysisLog;
import com.example.demo.service.AnalysisLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class AnalysisLogController{

private final AnalysisLogService analysisLogService;

public AnalysisLogController(AnalysisLogService analysisLogService) {
this.analysisLogService = analysisLogService;
}

// POST /logs/{zoneId} – add analysis log
@PostMapping("/{zoneId}")
public ResponseEntity<AnalysisLog> addLog(@PathVariable("zoneId") Long zoneId,
@RequestBody AnalysisLog log) {
AnalysisLog saved = analysisLogService.addLog(zoneId, log.getMessage());
return ResponseEntity.ok(saved);
}

// GET /logs/zone/{zoneId} – fetch logs for zone
@GetMapping("/zone/{zoneId}")
public ResponseEntity<List<AnalysisLog>> getLogs(@PathVariable("zoneId") Long zoneId) {
return ResponseEntity.ok(analysisLogService.getLogsByZone(zoneId));
}
}
