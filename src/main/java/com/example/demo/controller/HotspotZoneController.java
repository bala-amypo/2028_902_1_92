
package com.example.demo.controller;

import com.example.demo.model.HotspotZone;
import com.example.demo.service.HotspotZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
@Tag(name = "Hotspot Zones")
@SecurityRequirement(name = "bearerAuth")
public class HotspotZoneController {

private final HotspotZoneService hotspotZoneService;

public HotspotZoneController(HotspotZoneService hotspotZoneService) {
this.hotspotZoneService = hotspotZoneService;
}

@PostMapping
@Operation(summary = "Create hotspot zone")
public ResponseEntity<HotspotZone> addZone(@RequestBody HotspotZone zone) {
HotspotZone saved = hotspotZoneService.addZone(zone);
return ResponseEntity.ok(saved);
}

@GetMapping
@Operation(summary = "List all hotspot zones")
public ResponseEntity<List<HotspotZone>> getAllZones() {
return ResponseEntity.ok(hotspotZoneService.getAllZones());
}
}