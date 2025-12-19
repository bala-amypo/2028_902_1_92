package com.example.demo.controller;

import com.example.demo.model.HotspotZone;
import com.example.demo.service.HotspotZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
public class HotspotZoneController {

private final HotspotZoneService ZoneService;

public demoZoneController(demoZoneService demoZoneService) {
this.demoZoneService = demoZoneService;
}

@PostMapping
public ResponseEntity<demoZone> addZone(@RequestBody demoZone zone) {
demoZone saved = demoZoneService.addZone(zone);
return ResponseEntity.ok(saved);
}

@GetMapping
public ResponseEntity<List<demoZone>> getAllZones() {
return ResponseEntity.ok(demoZoneService.getAllZones());
}
}
