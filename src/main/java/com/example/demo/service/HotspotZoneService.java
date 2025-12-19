
package com.example.demo.repository;

import com.example.demo.model.demoZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotspotZoneRepository extends JpaRepository<demoZone, Long> {

List<HotspotZone> findBySeverityLevel(String level);

Optional<HotspotZone> findByZoneName(String zoneName);
}

