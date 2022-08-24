package com.kk.strong.service;

import com.kk.strong.repository.BodyReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BodyReportService {

    private final BodyReportRepository bodyReportRepository;


}
