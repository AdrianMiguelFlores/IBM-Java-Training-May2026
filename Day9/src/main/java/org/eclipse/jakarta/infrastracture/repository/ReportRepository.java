package org.eclipse.jakarta.infrastracture.repository;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jakarta.dto.ReportDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReportRepository {
    private List<ReportDto> reports = new ArrayList<>();

    public List<ReportDto> findAll() {
        return reports;
    }

    public void create(ReportDto report) {
        reports.add(report);
    }
    
    public ReportDto findByIndex(Integer index) {
        if (index != null && index >= 0 && index < reports.size()) {
            return reports.get(index);
        }
        return null;
    }

    public void update(Integer index, ReportDto updatedReport) {
        if (index != null && index >= 0 && index < reports.size()) {
            reports.set(index, updatedReport);
        }
    }
    
    public void delete(Integer index) {
        if (index != null && index >= 0 && index < reports.size()) {
            reports.remove(index.intValue());
        }
    }
}