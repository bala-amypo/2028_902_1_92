
package com.example.demo.controller;

import com.example.demo.model.CrimeReport;
import com.example.demo.service.CrimeReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@Tag(name = "Crime Reports")
@SecurityRequirement(name = "bearerAuth")
public class CrimeReportController {

private final CrimeReportService crimeReportService;

public CrimeReportController(CrimeReportService crimeReportService) {
this.crimeReportService = crimeReportService;
}

@PostMapping
@Operation(summary = "Add a new crime report")
public ResponseEntity<CrimeReport> addReport(@RequestBody CrimeReport request) {
CrimeReport saved = crimeReportService.addReport(request);
return ResponseEntity.ok(saved);
}

@GetMapping
@Operation(summary = "Fetch all crime reports")
public ResponseEntity<List<CrimeReport>> getAllReports() {
return ResponseEntity.ok(crimeReportService.getAllReports());
}
}
