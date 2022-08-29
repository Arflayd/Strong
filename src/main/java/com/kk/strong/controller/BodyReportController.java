package com.kk.strong.controller;

import com.kk.strong.model.BodyReport;
import com.kk.strong.service.BodyReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('REGULAR_USER')")
public class BodyReportController {

    private final BodyReportService bodyReportService;

    @GetMapping("/body_reports")
    public ResponseEntity<List<BodyReport>> getBodyReports() {
        return ResponseEntity.ok(bodyReportService.getBodyReports());
    }

    @GetMapping("/body_report/{bodyReportId}")
    public ResponseEntity<BodyReport> getBodyReport(@PathVariable Long bodyReportId) {
        return ResponseEntity.ok(bodyReportService.getBodyReport(bodyReportId));
    }

    @PutMapping("/body_report/{bodyReportId}")
    public ResponseEntity<?> updateBodyReport(@PathVariable Long bodyReportId, @RequestBody BodyReport bodyReport) {
        bodyReportService.updateBodyReport(bodyReportId, bodyReport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/body_report/{bodyReportId}")
    public ResponseEntity<?> deleteBodyReport(@PathVariable Long bodyReportId) {
        bodyReportService.deleteBodyReport(bodyReportId);
        return ResponseEntity.noContent().build();
    }
}
