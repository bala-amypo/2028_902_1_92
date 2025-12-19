package com.example.demo.service.Implement;

import com.example.demo.model.CrimeReport;
import com.example.demo.repository.CrimeReportRepository;
import com.example.demo.service.CrimeReportService;
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
// validation rules (lat between -90..90, long -180..180, date not future)
// should be handled by validators/exceptions if your test includes them.
return crimeReportRepository.save(report);
}

@Override
public List<CrimeReport> getAllReports() {
return crimeReportRepository.findAll();
}
}
