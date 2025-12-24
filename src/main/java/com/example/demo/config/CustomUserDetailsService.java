package com.example.demo.util;

import java.time.LocalDateTime;

public class DateValidator {

private DateValidator() {
}

public static void validateReportDate(LocalDateTime reportedAt) {
if (reportedAt == null) {
throw new IllegalArgumentException("Reported date cannot be null");
}

LocalDateTime now = LocalDateTime.now();
if (reportedAt.isAfter(now)) {
throw new IllegalArgumentException("Reported date cannot be in the future");
}

LocalDateTime oneYearAgo = now.minusYears(1);
if (reportedAt.isBefore(oneYearAgo)) {
throw new IllegalArgumentException("Reported date cannot be more than 1 year old");
}
}
}
