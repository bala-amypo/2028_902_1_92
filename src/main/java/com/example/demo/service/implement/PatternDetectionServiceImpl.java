
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnalysisLog;
import com.example.demo.model.CrimeReport;
import com.example.demo.model.HotspotZone;
import com.example.demo.model.PatternDetectionResult;
import com.example.demo.repository.AnalysisLogRepository;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.repository.PatternDetectionResultRepository;
import com.example.demo.service.PatternDetectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatternDetectionServiceImpl implements PatternDetectionService {

private final HotspotZoneRepository zoneRepository;
private final CrimeReportRepository crimeReportRepository;
private final PatternDetectionResultRepository resultRepository;
private final AnalysisLogRepository analysisLogRepository;

public PatternDetectionServiceImpl(HotspotZoneRepository zoneRepository,
CrimeReportRepository crimeReportRepository,
PatternDetectionResultRepository resultRepository,
AnalysisLogRepository analysisLogRepository) {
this.zoneRepository = zoneRepository;
this.crimeReportRepository = crimeReportRepository;
this.resultRepository = resultRepository;
this.analysisLogRepository = analysisLogRepository;
}

@Override
public PatternDetectionResult detectPattern(Long zoneId) {

HotspotZone zone = zoneRepository.findById(zoneId)
.orElseThrow(() -> new ResourceNotFoundException("zone not found"));

double lat = zone.getCenterLat();
double lon = zone.getCenterLong();
double minLat = lat - 0.1;
double maxLat = lat + 0.1;
double minLong = lon - 0.1;
double maxLong = lon + 0.1;

List<CrimeReport> crimes =
crimeReportRepository.findByLatLongRange(
minLat, maxLat, minLong, maxLong);

int count = crimes.size();

String pattern;
if (count > 5) {
pattern = "High density";
zone.setSeverityLevel("HIGH");
} else if (count > 0) {
pattern = "Medium density";
zone.setSeverityLevel("MEDIUM");
} else {
pattern = "No significant pattern";
zone.setSeverityLevel("LOW");
}

PatternDetectionResult result =
new PatternDetectionResult(zone, LocalDate.now(), count, pattern);
PatternDetectionResult savedResult = resultRepository.save(result);

// save updated zone severity
zoneRepository.save(zone);

// analysis log entry
AnalysisLog log = new AnalysisLog(
"Pattern detection for zone " + zone.getZoneName()
+ " => " + pattern + ", count=" + count,
zone
);
analysisLogRepository.save(log);

return savedResult;
}

@Override
public List<PatternDetectionResult> getResultsByZone(Long zoneId) {
return resultRepository.findByZone_Id(zoneId);
}
}
