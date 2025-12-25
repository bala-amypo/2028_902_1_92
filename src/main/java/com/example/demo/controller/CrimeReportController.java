package com.example.demo.controller;

import com.example.demo.model.CrimeReport;
import com.example.demo.service.CrimeReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reports")
@Tag(name = "Crime Reports")
public class CrimeReportController {
    private final CrimeReportService reportService;
    
    public CrimeReportController(CrimeReportService reportService) {
        this.reportService = reportService;
    }
    
    @PostMapping
    @Operation(summary = "Create crime report")
    public ResponseEntity<CrimeReport> addReport(@RequestBody CrimeReport report) {
        CrimeReport savedReport = reportService.addReport(report);
        return ResponseEntity.ok(savedReport);
    }
    
    @GetMapping
    @Operation(summary = "Get all crime reports")
    public ResponseEntity<List<CrimeReport>> getAllReports() {
        List<CrimeReport> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}