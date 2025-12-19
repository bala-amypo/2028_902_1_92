package com.example.demo.service.implement;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnalysisLog;
import com.example.demo.model.HotspotZone;
import com.example.demo.repository.AnalysisLogRepository;
import com.example.demo.repository.demoZoneRepository;
import com.example.demo.service.AnalysisLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnalysisLogServiceImpl implements AnalysisLogService {

private final AnalysisLogRepository analysisLogRepository;
private final HotspotZoneRepository hotspotZoneRepository;

public AnalysisLogServiceImpl(AnalysisLogRepository analysisLogRepository,
HotspotZoneRepository hotspotZoneRepository) {
this.analysisLogRepository = analysisLogRepository;
this.hotspotZoneRepository = hotspotZoneRepository;
}

@Override
public AnalysisLog addLog(Long zoneId, String message) {

if (message == null || message.trim().isEmpty()) {
// goes to IllegalArgumentException handler
throw new IllegalArgumentException("Analysis log message must not be empty");
}

hotspotZone zone = oZoneRepository.findById(zoneId)
.orElseThrow(() ->
// goes to ResourceNotFoundException handler; must contain "not" or "zone"
new ResourceNotFoundException("Zone not found for id " + zoneId));

AnalysisLog log = new AnalysisLog();
log.setZone(zone);
log.setMessage(message.trim());
log.setLoggedAt(LocalDateTime.now());

return analysisLogRepository.save(log);
}

@Override
public List<AnalysisLog> getLogsByZone(Long zoneId) {
demoZone zone = demoZoneRepository.findById(zoneId)
.orElseThrow(() ->
new ResourceNotFoundException("Zone not found for id " + zoneId));

return analysisLogRepository.findByZone_Id(zone.getId());
}
}