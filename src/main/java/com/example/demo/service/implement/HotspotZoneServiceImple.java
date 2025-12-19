
package com.example.demo.service.Implement;

import com.example.demo.model.demoZone;
import com.example.demo.repository.demoZoneRepository;
import com.example.demo.service.demoZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class demoZoneServiceImpl implements demoZoneService {

private final demoZoneRepository demoZoneRepository;

public demoZoneServiceImpl(demoZoneRepository demoZoneRepository) {
this.demoZoneRepository = demoZoneRepository;
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