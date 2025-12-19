package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AnalysisLog {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String message;

private LocalDateTime loggedAt;

@ManyToOne(optional = false)
private demoZone zone;

public AnalysisLog() {
}

public AnalysisLog(Long id, String message, LocalDateTime loggedAt, demoZone zone) {
this.id = id;
this.message = message;
this.loggedAt = loggedAt;
this.zone = zone;
}

@PrePersist
public void prePersist() {
if (loggedAt == null) {
loggedAt = LocalDateTime.now();
}
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public LocalDateTime getLoggedAt() {
return loggedAt;
}

public void setLoggedAt(LocalDateTime loggedAt) {
this.loggedAt = loggedAt;
}

public demoZone getZone() {
return zone;
}

public void setZone(demoZone zone) {
this.zone = zone;
}

@Override
public String toString() {
return "AnalysisLog{" +
"id=" + id +
", message='" + message + '\'' +
", loggedAt=" + loggedAt +
", zone=" + (zone != null ? zone.getId() : null) +
'}';
}
}
