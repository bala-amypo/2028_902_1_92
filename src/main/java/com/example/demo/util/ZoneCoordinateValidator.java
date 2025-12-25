
package com.example.demo.util;

import com.example.demo.model.HotspotZone;

public class ZoneCoordinateValidator {

private ZoneCoordinateValidator() {
}

public static void validateZone(HotspotZone zone) {
if (zone.getCenterLat() == null ||
zone.getCenterLat() < -90 || zone.getCenterLat() > 90) {
throw new IllegalArgumentException("Invalid latitude for zone");
}

if (zone.getCenterLong() == null ||
zone.getCenterLong() < -180 || zone.getCenterLong() > 180) {
throw new IllegalArgumentException("Invalid longitude for zone");
}
}
}