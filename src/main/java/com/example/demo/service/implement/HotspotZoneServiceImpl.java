
package com.example.demo.service.impl;

import com.example.demo.model.HotspotZone;
import com.example.demo.repository.HotspotZoneRepository;
import com.example.demo.service.HotspotZoneService;
import com.example.demo.util.ZoneCoordinateValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotspotZoneServiceImpl implements HotspotZoneService {

private final HotspotZoneRepository hotspotZoneRepository;

public HotspotZoneServiceImpl(HotspotZoneRepository hotspotZoneRepository) {
this.hotspotZoneRepository = hotspotZoneRepository;
}

@Override
public HotspotZone addZone(HotspotZone zone) {

// uniqueness by name
if (hotspotZoneRepository.existsByZoneName(zone.getZoneName())) {
// message must contain "exists"
throw new IllegalArgumentException("Zone name already exists");
}

// coordinate validation using util
ZoneCoordinateValidator.validateZone(zone);

return hotspotZoneRepository.save(zone);
}

@Override
public List<HotspotZone> getAllZones() {
return hotspotZoneRepository.findAll();
}
}


