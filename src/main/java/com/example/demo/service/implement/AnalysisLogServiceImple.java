

package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnalysisLog;
import com.example.demo.model.HotspotZone;
import com.example.demo.repository.AnalysisLogRepository;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.AnalysisLogService;
import org.springframework.stereotype.Service;

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

HotspotZone zone = hotspotZoneRepository.findById(zoneId)
.orElseThrow(() -> new ResourceNotFoundException("zone not found"));

AnalysisLog log = new AnalysisLog(message, zone);
return analysisLogRepository.save(log);
}

@Override
public List<AnalysisLog> getLogsByZone(Long zoneId) {
return analysisLogRepository.findByZone_Id(zoneId);
}
}