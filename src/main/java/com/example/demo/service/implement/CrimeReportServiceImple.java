
package com.example.demo.service.impl;

import com.example.demo.model.CrimeReport;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.service.CrimeReportService;
import com.example.demo.util.CoordinateValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrimeReportServiceImpl implements CrimeReportService {

private final CrimeReportRepository crimeReportRepository;

public CrimeReportServiceImpl(CrimeReportRepository crimeReportRepository) {
this.crimeReportRepository = crimeReportRepository;
}

@Override
public CrimeReport addReport(CrimeReport report) {

// centralised validation
CoordinateValidator.validateCrimeReport(report);

return crimeReportRepository.save(report);
}

@Override
public List<CrimeReport> getAllReports() {
return crimeReportRepository.findAll();
}
}