package com.kk.strong.repository;

import com.kk.strong.model.BodyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BodyReportRepository extends JpaRepository<BodyReport, Long> {

}
