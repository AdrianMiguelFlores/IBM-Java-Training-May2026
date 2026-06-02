package org.eclipse.jakarta.backingbean;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;

@Named
@RequestScoped
public class ReportUpdateBean {

    private Integer index;
    
    @NotBlank(message = "You cannot leave the title blank.")
    private String title;
    private String detail;

    @Inject
    private ReportRepository reportRepository;

    public void loadReport() {
        ReportDto existingReport = reportRepository.findByIndex(index);
        if (existingReport != null) {
            this.title = existingReport.getTitle();
            this.detail = existingReport.getDetail();
        }
    }

    public String update() {
        ReportDto updatedReport = new ReportDto();
        updatedReport.setTitle(title);
        updatedReport.setDetail(detail);
        
        reportRepository.update(index, updatedReport);
        
        return "/reportList.xhtml?faces-redirect=true";
    }

    public Integer getIndex() { 
    	return index; 
    }
    
    public void setIndex(Integer index) { 
    	this.index = index; 
    }

    public String getTitle() { 
    	return title; 
    }
    
    public void setTitle(String title) { 
    	this.title = title; 
    }

    public String getDetail() { 
    	return detail; 
    }
    
    public void setDetail(String detail) { 
    	this.detail = detail; 
    }
    
}
