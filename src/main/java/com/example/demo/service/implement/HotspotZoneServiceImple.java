
package com.example.demo.service.implement;

import com.example.demo.model.HotspotZone;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.HotspotZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotspotZoneServiceImpl implements HotspotZoneService {

private final HotspotZoneRepository hotspotZoneRepository;

public demoZoneServiceImpl(HotspotZoneRepository hotspotZoneRepository) {
this.hotspotZoneRepository = demoZoneRepository;
}

@Override
public demoZone addZone(demoZone zone) {
// uniqueness & coordinate validation can be handled by validators
return demoZoneRepository.save(zone);
}

@Override
public List<demoZone> getAllZones() {
return demoZoneRepository.findAll();
}
}