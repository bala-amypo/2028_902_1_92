
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PatternDetectionResult {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(optional = false)
private demoZone zone;

private LocalDate analysisDate;

private Integer crimeCount;

// text must describe findings (High / Medium / No) per Step 0
private String detectedPattern;

public PatternDetectionResult() {
}

public PatternDetectionResult(Long id, demoZone zone,
LocalDate analysisDate, Integer crimeCount,
String detectedPattern) {
this.id = id;
this.zone = zone;
this.analysisDate = analysisDate;
this.crimeCount = crimeCount;
this.detectedPattern = detectedPattern;
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public demoZone getZone() {
return zone;
}

public void setZone(demoZone zone) {
this.zone = zone;
}

public LocalDate getAnalysisDate() {
return analysisDate;
}

public void setAnalysisDate(LocalDate analysisDate) {
this.analysisDate = analysisDate;
}

public Integer getCrimeCount() {
return crimeCount;
}

public void setCrimeCount(Integer crimeCount) {
this.crimeCount = crimeCount;
}

public String getDetectedPattern() {
return detectedPattern;
}

public void setDetectedPattern(String detectedPattern) {
this.detectedPattern = detectedPattern;
}

@Override
public String toString() {
return "PatternDetectionResult{" +
"id=" + id +
", zone=" + (zone != null ? zone.getId() : null) +
", analysisDate=" + analysisDate +
", crimeCount=" + crimeCount +
", detectedPattern='" + detectedPattern + '\'' +
'}';
}
}
