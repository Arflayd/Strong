package com.kk.strong.service;

import com.kk.strong.exception.BodyReportNotFoundException;
import com.kk.strong.model.BodyReport;
import com.kk.strong.repository.BodyReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BodyReportService {

    private final BodyReportRepository bodyReportRepository;

    public List<BodyReport> getBodyReports() {
        return bodyReportRepository.findAll();
    }

    public BodyReport getBodyReport(Long bodyReportId) {
        return bodyReportRepository
                .findById(bodyReportId)
                .orElseThrow(() -> new BodyReportNotFoundException(bodyReportId));
    }

    public BodyReport createBodyReport(BodyReport bodyReport){
        return bodyReportRepository.save(bodyReport);
    }

    public void updateBodyReport(Long bodyReportId, BodyReport bodyReport) {
        bodyReportRepository.save(bodyReport);
    }

    public void deleteBodyReport(Long bodyReportId) {
        bodyReportRepository.deleteById(bodyReportId);
    }
}
