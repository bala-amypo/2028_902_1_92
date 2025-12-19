
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
this.hotspotZoneRepository = hotspotZoneRepository;
}

@Override
public HotspotZone addZone(HotspotZone zone) {
// uniqueness & coordinate validation can be handled by validators
return HotspotZoneRepository.save(zone);
}

@Override
public List<HotspotZone> getAllZones() {
return HotspotZoneRepository.findAll();
}
}