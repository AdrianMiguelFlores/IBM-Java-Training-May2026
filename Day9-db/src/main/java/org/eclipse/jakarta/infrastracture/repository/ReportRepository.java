package org.eclipse.jakarta.infrastracture.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.entity.Report;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ReportRepository {

    @PersistenceContext
    private EntityManager em;

    private ReportDto convertToDto(Report entity) {
        if (entity == null) {
        	return null;
        }
        ReportDto report = new ReportDto();
        
        report.setTitle(entity.getTitle());
        report.setDetail(entity.getDescription());
        
        return report;
    }

    private Report convertToEntity(ReportDto dto) {
        if (dto == null) {
        	return null;
        }
        
        Report entity = new Report();
        
        entity.setTitle(dto.getTitle());
        entity.setDetail(dto.getDetail());
        
        return entity;
    }

    private List<Report> getAllEntities() {
        return em.createQuery("SELECT r FROM Report r ORDER BY r.id ASC", Report.class).getResultList();
    }

    public List<ReportDto> findAll() {
        return getAllEntities().stream()
                               .map(this::convertToDto)
                               .collect(Collectors.toList());
    }

    @Transactional
    public void create(ReportDto reportDto) {
        Report entity = convertToEntity(reportDto);
        em.persist(entity);
    }
    
    public ReportDto findByIndex(Integer index) {
        if (index == null || index < 0) {
        	return null;
        }
        
        List<Report> entities = getAllEntities();
        if (index < entities.size()) {
            return convertToDto(entities.get(index));
        }
        return null;
    }

    @Transactional
    public void update(Integer index, ReportDto updatedReportDto) {
        if (index == null || index < 0 || updatedReportDto == null) {
        	return;
        }
        
        List<Report> entities = getAllEntities();
        if (index < entities.size()) {
            Report existingEntity = entities.get(index);
            existingEntity.setTitle(updatedReportDto.getTitle());
            existingEntity.setDetail(updatedReportDto.getDetail());
            em.merge(existingEntity);
        }
    }
    
    @Transactional
    public void delete(Integer index) {
        if (index == null || index < 0) {
        	return;
        }
        
        List<Report> entities = getAllEntities();
        if (index < entities.size()) {
            Report entity = entities.get(index);
            em.remove(em.merge(entity));
        }
    }
}