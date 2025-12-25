package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pattern_detection_results")
public class PatternDetectionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private HotspotZone zone;
    
    private LocalDate analysisDate;
    private Integer crimeCount;
    private String detectedPattern;
    
    public PatternDetectionResult() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public HotspotZone getZone() { return zone; }
    public void setZone(HotspotZone zone) { this.zone = zone; }
    
    public LocalDate getAnalysisDate() { return analysisDate; }
    public void setAnalysisDate(LocalDate analysisDate) { this.analysisDate = analysisDate; }
    
    public Integer getCrimeCount() { return crimeCount; }
    public void setCrimeCount(Integer crimeCount) { this.crimeCount = crimeCount; }
    
    public String getDetectedPattern() { return detectedPattern; }
    public void setDetectedPattern(String detectedPattern) { this.detectedPattern = detectedPattern; }
}