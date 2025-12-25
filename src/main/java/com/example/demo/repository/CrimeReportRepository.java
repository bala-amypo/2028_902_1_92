package com.example.demo.repository;

import com.example.demo.model.CrimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CrimeReportRepository extends JpaRepository<CrimeReport, Long> {
    @Query("SELECT c FROM CrimeReport c WHERE c.latitude BETWEEN :minLat AND :maxLat AND c.longitude BETWEEN :minLong AND :maxLong")
    List<CrimeReport> findByLatLongRange(@Param("minLat") double minLat, @Param("maxLat") double maxLat, 
                                        @Param("minLong") double minLong, @Param("maxLong") double maxLong);
}