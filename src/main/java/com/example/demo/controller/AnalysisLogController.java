package com.example.demo.controller;

import com.example.demo.model.AnalysisLog;
import com.example.demo.service.AnalysisLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/logs")
@Tag(name = "Analysis Logs")
public class AnalysisLogController {
    private final AnalysisLogService logService;
    
    public AnalysisLogController(AnalysisLogService logService) {
        this.logService = logService;
    }
    
    @PostMapping("/{zoneId}")
    @Operation(summary = "Add analysis log")
    public ResponseEntity<AnalysisLog> addLog(@PathVariable Long zoneId, @RequestBody String message) {
        AnalysisLog log = logService.addLog(zoneId, message);
        return ResponseEntity.ok(log);
    }
    
    @GetMapping("/zone/{zoneId}")
    @Operation(summary = "Get logs for zone")
    public ResponseEntity<List<AnalysisLog>> getLogsByZone(@PathVariable Long zoneId) {
        List<AnalysisLog> logs = logService.getLogsByZone(zoneId);
        return ResponseEntity.ok(logs);
    }
}