package org.learn.javatraining.day7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogAnalyzerTest {
    
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(buffer));
    }

    @AfterEach
    void cleanUp() throws IOException {
        System.setOut(originalOut);
    }
    
    /**
     * should_PrintConsoleMessage_When_CorrectLogs
     * should print console message when the logs are correct
     */
    @Test
    void exec001() throws IOException {
        // given
    	String filePath = "src/test/resources/exec001/server.log";
        String expected = "Analysis complete. Summary written to summary.txt";

        // when
        LogAnalyzer.main(new String[] {filePath});
        String actual = buffer.toString().trim();

        // then
        assertEquals(expected, actual);
    }
    
    /**
     * should_EqualSummaryReport_When_AnalysisSuccessful
     * should return equal when log summary analysis is successful
     */
    @Test
    void exec002() throws IOException {
    	// given
    	String expectedFile = Files.readString(Path.of("src/test/resources/exec002/summary.txt")).replace("\r\n", "\n").strip();
    	String filePath = "src/test/resources/exec002/server.log";
    	
        // when
        LogAnalyzer.main(new String[] {filePath});
        
        String summaryFile = Files.readString(Path.of("resources/summary.txt")).replace("\r\n", "\n").strip();
        
        // then
        assertEquals(expectedFile, summaryFile);
    }
    
    /**
     * should_EqualSummaryReport_When_MalformedLineEncountered
     * should have equal summary report even malformed line is encountered
     */
    @Test
    void exec003() throws IOException {        
        // given
    	String expectedFile = Files.readString(Path.of("src/test/resources/exec003/summary.txt")).replace("\r\n", "\n").strip();
    	String filePath = "src/test/resources/exec003/server.log";
    	
        // when
        LogAnalyzer.main(new String[] {filePath});
        
        String summaryFile = Files.readString(Path.of("resources/summary.txt")).replace("\r\n", "\n").strip();
        
        // then
        assertEquals(expectedFile, summaryFile);
    }
    
    /**
     * should_PrintIOExceptionMessage_When_ReadFailureOccurs
     * should print IOException message when reading the file failed (e.g. locked file)
     */
    @Test
    void exec004() throws IOException {
        // given
        String filename = "src/test/resources/exec004/server.log";
        String expected = "Error reading file.";
        Path testPath = Path.of(filename);

        // when
        try (FileChannel channel = FileChannel.open(testPath, StandardOpenOption.WRITE);
             FileLock lock = channel.lock()) {

            LogAnalyzer.main(new String[] {filename});
        }
        String actual = buffer.toString().trim();

        // then
        assertEquals(expected, actual);
    }
    
    /**
     * should_PrintIOExceptionMessage_When_WriteFailureOccurs
     * should print IOException message when writing the file failed
     */
    @Test
    void exec005() throws IOException {
        // given
    	String filename = "resources/summary.txt";
        String expected = "Error writing summary file.";
        Path summaryPath = Path.of(filename);
        Files.createDirectories(summaryPath.getParent());
        Files.writeString(summaryPath, ""); 
        summaryPath.toFile().setWritable(false);

        // when
        try {
            LogAnalyzer.main(new String[] {filename});
        } finally {
            summaryPath.toFile().setWritable(true);
        }
        String actual = buffer.toString().trim();

        // then
        assertTrue(actual.contains(expected));
    }
    
    /**
     * should_PrintFileNotFoundExceptionMessage_When_ReadFileNotExist
     * should print FileNotFoundException when the read file does not exist
     */
    @Test
    void exec006() {
        // given
        String[] args = {"notExistingFile.log"};
        String expected = "Log file not found.";

        // when
        LogAnalyzer.main(args);
        String actual = buffer.toString().trim();

        // then
        assertEquals(expected, actual);
    }
}