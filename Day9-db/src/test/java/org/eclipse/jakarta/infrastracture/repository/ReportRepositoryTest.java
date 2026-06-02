package org.eclipse.jakarta.infrastracture.repository;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.jakarta.dto.ReportDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReportRepositoryTest {

	private ReportRepository repository;
    private ReportDto testReport1;
    private ReportDto testReport2;

    @BeforeEach
    void setUp() {
        repository = new ReportRepository();

        testReport1 = new ReportDto();
        testReport1.setTitle("Test Report 1");
        testReport1.setDetail("This is the first test report");

        testReport2 = new ReportDto();
        testReport2.setTitle("Test Report 2");
        testReport2.setDetail("This is the second test report");

        System.out.println("@BeforeEach: Test setup completed");
    }

    @AfterEach
    void tearDown() {
        repository = null;
        testReport1 = null;
        testReport2 = null;
        System.out.println("@AfterEach: Test cleanup completed");
    }

    @Test
    @DisplayName("Should create a new report")
    void testCreate() {
        repository.create(testReport1);
        
        assertAll(
	        () -> assertNotNull(testReport1, "Test Report should not be null when instantiated"),
	        () -> assertEquals("Test Report 1", testReport1.getTitle(), "Title should be equal when initialized"),
	        () -> assertEquals("This is the first test report", testReport1.getDetail(), "Detail should be equal when initialized"),
	        () -> assertEquals(1, repository.findAll().size(), "Repository should contain 1 report")
        );
    }
    
    @Test
    @DisplayName("Should update existing report")
    void testUpdate() {
    	repository.create(testReport1);
    	
    	repository.update(0, testReport2);
    	
    	assertAll(
    		() -> assertEquals("Test Report 2", repository.findAll().getFirst().getTitle(), "Title of first index should change when updated"),
    		() -> assertEquals("This is the second test report", repository.findAll().getFirst().getDetail(), "Detail of first index should change when updated")
    	);
    }
    
    @Test
    @DisplayName("Should delete a report")
    void testDelete() {
    	repository.create(testReport1);
    	
    	repository.delete(0);
    	
    	assertTrue(repository.findAll().isEmpty(), "Should be true when repository is empty");
    }
    
    @Test
    @DisplayName("Should delete properly even with multiple reports")
    void testDeleteWithMultipleData() {
    	repository.create(testReport1);
    	repository.create(testReport2);
    	
    	repository.delete(0);
    	
    	assertAll(
    		() -> assertEquals("Test Report 2", testReport2.getTitle(), "Title should exist when report is not deleted"),
    	    () -> assertEquals("This is the second test report", testReport2.getDetail(), "Detail should exist when report is not deleted")
    	);
    }
    
    @Test
    @DisplayName("Should return existing report")
    void testView() {
    	repository.create(testReport1);
    	
    	ReportDto firstReport = repository.findByIndex(0);
    	ReportDto secondReport = repository.findByIndex(1);
    	
    	assertAll(
    		() -> assertNull(secondReport), // should be null when report does not exist
    		() -> assertEquals(testReport1, firstReport, "Should be equal when fetched report is correct")
    	);
    }
}
