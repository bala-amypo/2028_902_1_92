

package com.example.demo.service.Implement;

import com.example.demo.model.CrimeReport;
import com.example.demo.model.demoZone;
import com.example.demo.model.PatternDetectionResult;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.repository.demoZoneRepository;
import com.example.demo.repository.PatternDetectionResultRepository;
import com.example.demo.service.PatternDetectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatternDetectionServiceImpl implements PatternDetectionService {

private final demoZoneRepository demoZoneRepository;
private final CrimeReportRepository crimeReportRepository;
private final PatternDetectionResultRepository patternDetectionResultRepository;

public PatternDetectionServiceImpl(demoZoneRepository demoZoneRepository,
CrimeReportRepository crimeReportRepository,
PatternDetectionResultRepository patternDetectionResultRepository) {
this.demoZoneRepository = demoZoneRepository;
this.crimeReportRepository = crimeReportRepository;
this.patternDetectionResultRepository = patternDetectionResultRepository;
}

@Override
public PatternDetectionResult detectPattern(Long zoneId) {
demoZone zone = demoZoneRepository.findById(zoneId)
.orElseThrow(() -> new RuntimeException("Zone not found"));

double minLat = zone.getCenterLat() - 0.1;
double maxLat = zone.getCenterLat() + 0.1;
double minLong = zone.getCenterLong() - 0.1;
double maxLong = zone.getCenterLong() + 0.1;

List<CrimeReport> reports =
crimeReportRepository.findByLatLongRange(minLat, maxLat, minLong, maxLong);

int crimeCount = reports.size();

String pattern;
if (crimeCount > 5) {
pattern = "High";
zone.setSeverityLevel("HIGH");
} else if (crimeCount > 0) {
pattern = "Medium";
zone.setSeverityLevel("MEDIUM");
} else {
pattern = "No";
zone.setSeverityLevel("LOW");
}

demoZoneRepository.save(zone);

PatternDetectionResult result = new PatternDetectionResult();
result.setZone(zone);
result.setAnalysisDate(LocalDate.now());
result.setCrimeCount(crimeCount);
result.setDetectedPattern(pattern);

return patternDetectionResultRepository.save(result);
}

@Override
public List<PatternDetectionResult> getResultsByZone(Long zoneId) {
return patternDetectionResultRepository.findByZone_Id(zoneId);
}
}
