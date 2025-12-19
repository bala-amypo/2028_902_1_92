package com.example.demo.repository;

import com.example.demo.model.HotspotZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface demoZoneRepository extends JpaRepository<demoZone, Long> {

List<HotZone> findBySeverityLevel(String level);

Optional<demoZone> findByZoneName(String zoneName);
}

