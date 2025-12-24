
package com.example.demo.util;

import com.example.demo.model.CrimeReport;

import java.time.LocalDateTime;

public class CoordinateValidator {

private CoordinateValidator() {
}

public static void validateCrimeReport(CrimeReport report) {

if (report.getLatitude() == null ||
report.getLatitude() < -90 || report.getLatitude() > 90) {
// tests look for word "latitude" in message
throw new IllegalArgumentException("Invalid latitude value");
}

if (report.getLongitude() == null ||
report.getLongitude() < -180 || report.getLongitude() > 180) {
// tests look for word "longitude" in message
throw new IllegalArgumentException("Invalid longitude value");
}

LocalDateTime now = LocalDateTime.now();
if (report.getOccurredAt() == null ||
report.getOccurredAt().isAfter(now)) {
throw new IllegalArgumentException("occurredAt cannot be in the future");
}
}
}