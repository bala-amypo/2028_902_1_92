package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class HotspotZone {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(unique = true, nullable = false)
private String zoneName;

private Double centerLat;

private Double centerLong;

// LOW / MEDIUM / HIGH
@Column(nullable = false)
private String severityLevel;

public demoZone() {
}

public demoZone(Long id, String zoneName, Double centerLat,
Double centerLong, String severityLevel) {
this.id = id;
this.zoneName = zoneName;
this.centerLat = centerLat;
this.centerLong = centerLong;
this.severityLevel = severityLevel;
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getZoneName() {
return zoneName;
}

public void setZoneName(String zoneName) {
this.zoneName = zoneName;
}

public Double getCenterLat() {
return centerLat;
}

public void setCenterLat(Double centerLat) {
this.centerLat = centerLat;
}

public Double getCenterLong() {
return centerLong;
}

public void setCenterLong(Double centerLong) {
this.centerLong = centerLong;
}

public String getSeverityLevel() {
return severityLevel;
}

public void setSeverityLevel(String severityLevel) {
this.severityLevel = severityLevel;
}

@Override
public String toString() {
return "demoZone{" +
"id=" + id +
", zoneName='" + zoneName + '\'' +
", centerLat=" + centerLat +
", centerLong=" + centerLong +
", severityLevel='" + severityLevel + '\'' +
'}';
}
}
