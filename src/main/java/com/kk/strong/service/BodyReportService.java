package com.kk.strong.service;

import com.kk.strong.exception.BodyReportNotFoundException;
import com.kk.strong.model.BodyReport;
import com.kk.strong.model.WorkoutSession;
import com.kk.strong.model.dto.BodyReportDto;
import com.kk.strong.repository.BodyReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BodyReportService {

    private final BodyReportRepository bodyReportRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<BodyReportDto> getBodyReports() {
        log.info("Getting all body reports");
        return bodyReportRepository
                .findAll()
                .stream()
                .map(bodyReport -> modelMapper.map(bodyReport, BodyReportDto.class))
                .collect(Collectors.toList());
    }

    public BodyReportDto getBodyReport(Long bodyReportId) {
        log.info("Getting body report with id: {}", bodyReportId);
        BodyReport bodyReport = bodyReportRepository
                .findById(bodyReportId)
                .orElseThrow(() -> new BodyReportNotFoundException(bodyReportId));
        return modelMapper.map(bodyReport, BodyReportDto.class);
    }

    public void updateBodyReport(Long bodyReportId, BodyReportDto bodyReportDto) {
        log.info("Updating body report with id: {}", bodyReportId);
        BodyReport bodyReport = bodyReportRepository
                .findById(bodyReportId)
                .orElseThrow(() -> new BodyReportNotFoundException(bodyReportId));
        modelMapper.map(bodyReportDto, bodyReport);
    }

    public void deleteBodyReport(Long bodyReportId){
        log.info("Deleting body report with id: {}", bodyReportId);
        bodyReportRepository.deleteById(bodyReportId);
    }
}
