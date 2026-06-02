package org.eclipse.jakarta.backingbean;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ReportViewBean {

    private Integer index;
    private ReportDto report;

    @Inject
    private ReportRepository reportRepository;

    public void loadReport() {
        this.report = reportRepository.findByIndex(index);
    }

    public Integer getIndex() { 
    	return index; 
    }
    
    public void setIndex(Integer index) { 
    	this.index = index; 
    }

    public ReportDto getReport() { 
    	return report; 
    }
}
