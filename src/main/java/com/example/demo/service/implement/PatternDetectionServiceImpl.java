package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.PatternDetectionService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PatternDetectionServiceImpl implements PatternDetectionService {
    private final HotspotZoneRepository zoneRepo;
    private final CrimeReportRepository reportRepo;
    private final PatternDetectionResultRepository resultRepo;
    private final AnalysisLogRepository logRepo;
    
    public PatternDetectionServiceImpl(HotspotZoneRepository zoneRepo, CrimeReportRepository reportRepo, 
                                     PatternDetectionResultRepository resultRepo, AnalysisLogRepository logRepo) {
        this.zoneRepo = zoneRepo;
        this.reportRepo = reportRepo;
        this.resultRepo = resultRepo;
        this.logRepo = logRepo;
    }
    
    @Override
    public PatternDetectionResult detectPattern(Long zoneId) {
        HotspotZone zone = zoneRepo.findById(zoneId)
            .orElseThrow(() -> new RuntimeException("Zone not found"));
        
        double minLat = zone.getCenterLat() - 0.1;
        double maxLat = zone.getCenterLat() + 0.1;
        double minLong = zone.getCenterLong() - 0.1;
        double maxLong = zone.getCenterLong() + 0.1;
        
        List<CrimeReport> crimes = reportRepo.findByLatLongRange(minLat, maxLat, minLong, maxLong);
        int count = crimes.size();
        
        String pattern;
        String severity;
        if (count > 5) {
            pattern = "High density crime pattern detected";
            severity = "HIGH";
        } else if (count > 0) {
            pattern = "Medium density crime pattern detected";
            severity = "MEDIUM";
        } else {
            pattern = "No significant crime pattern detected";
            severity = "LOW";
        }
        
        PatternDetectionResult result = new PatternDetectionResult();
        result.setZone(zone);
        result.setCrimeCount(count);
        result.setDetectedPattern(pattern);
        result.setAnalysisDate(LocalDate.now());
        
        zone.setSeverityLevel(severity);
        zoneRepo.save(zone);
        
        AnalysisLog log = new AnalysisLog();
        log.setMessage("Pattern detection completed for zone " + zone.getZoneName());
        log.setZone(zone);
        logRepo.save(log);
        
        return resultRepo.save(result);
    }
    
    @Override
    public List<PatternDetectionResult> getResultsByZone(Long zoneId) {
        return resultRepo.findByZone_Id(zoneId);
    }
}