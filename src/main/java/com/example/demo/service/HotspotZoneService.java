
package com.example.demo.repository;

import com.example.demo.model.demoZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface demoZoneRepository extends JpaRepository<demoZone, Long> {

List<demoZone> findBySeverityLevel(String level);

Optional<demoZone> findByZoneName(String zoneName);
}

