package com.kk.strong.controller;

import com.kk.strong.model.BodyReport;
import com.kk.strong.model.dto.BodyReportDto;
import com.kk.strong.service.BodyReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('REGULAR_USER')")
@RequestMapping("/body_reports")
public class BodyReportController {

    private final BodyReportService bodyReportService;

    @GetMapping
    public ResponseEntity<List<BodyReportDto>> getBodyReports() {
        return ResponseEntity.ok(bodyReportService.getBodyReports());
    }

    @GetMapping("/{bodyReportId}")
    public ResponseEntity<BodyReportDto> getBodyReport(@PathVariable Long bodyReportId) {
        return ResponseEntity.ok(bodyReportService.getBodyReport(bodyReportId));
    }

    @PutMapping("/{bodyReportId}")
    public ResponseEntity<?> updateBodyReport(@PathVariable Long bodyReportId, @RequestBody BodyReportDto bodyReportDto) {
        bodyReportService.updateBodyReport(bodyReportId, bodyReportDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{bodyReportId}")
    public ResponseEntity<?> deleteBodyReport(@PathVariable Long bodyReportId){
        bodyReportService.deleteBodyReport(bodyReportId);
        return ResponseEntity.noContent().build();
    }
}
