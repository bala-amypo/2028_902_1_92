package com.example.demo.repository;

import com.example.demo.model.HotspotZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface demoZoneRepository extends JpaRepository<HotspotZone, Long> {

List<HotspotZone> findBySeverityLevel(String level);

Optional<HotspotZone> findByZoneName(String zoneName);
}

