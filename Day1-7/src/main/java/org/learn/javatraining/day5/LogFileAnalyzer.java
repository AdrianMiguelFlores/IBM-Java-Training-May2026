package org.learn.javatraining.day5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogFileAnalyzer {

	public static void main(String[] args) {
		Path logPath = Path.of("server.log");
		try {
			analyzeAndSummarizeLog(logPath);
		} catch (MalformedLogEntryException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void analyzeAndSummarizeLog(Path logPath) throws MalformedLogEntryException {
		Path summaryPath = Path.of("summary.txt");
		try (BufferedReader br = Files.newBufferedReader(logPath);
				BufferedWriter bw = Files.newBufferedWriter(summaryPath)) {
			Map<String, Integer> logCount = new HashMap<>();
			List<String> errorMessages = new ArrayList<>();
			List<LocalDateTime> timestamps = new ArrayList<>();

			String line;
			while ((line = br.readLine()) != null) {
				if (!line.contains(": ")) {
					throw new MalformedLogEntryException("Missing colon after the level");
				}

				String[] logParts = line.split(": ");
				String[] timestampLevelParts = logParts[0].split(" ");

				String message = logParts[1];
				String level = timestampLevelParts[2];
				String timestamp = String.join("T", timestampLevelParts[0], timestampLevelParts[1]);

				if (!timestamp.contains("[") || !timestamp.contains("]")) {
					throw new MalformedLogEntryException("Missing brackets for timestamp");
				}

				if (!level.equals("INFO") && !level.equals("WARN") && !level.equals("ERROR")) {
					throw new MalformedLogEntryException(level + " is an invalid Log Level");
				}

				if (level.equals("ERROR"))
					errorMessages.add(message);

				timestamps.add(LocalDateTime.parse(timestamp.substring(1, timestamp.length() - 1)));
				logCount.merge(level, 1, Integer::sum);
			}

			bw.write("Log Summary Report");
			bw.newLine();

			bw.write("------------------");
			bw.newLine();

			bw.write("Total Entries: " + logCount.entrySet().stream().mapToInt(entry -> entry.getValue()).sum());
			bw.newLine();

			for (Map.Entry<String, Integer> entry : logCount.entrySet()) {
				bw.write(entry.getKey() + ": " + entry.getValue());
				bw.newLine();
			}

			bw.newLine();

			bw.write("Error Messages:");
			bw.newLine();

			for (String message : errorMessages) {
				bw.write("- " + message);
				bw.newLine();
			}
			bw.newLine();

			timestamps.sort(Comparator.naturalOrder());

			bw.write("Earliest: " + timestamps.getFirst().toString().replace("T", " "));
			bw.newLine();

			bw.write("Latest: " + timestamps.getLast().toString().replace("T", " "));

		} catch (FileNotFoundException e) {
			System.out.println("File not found. " + e);
		} catch (IOException e) {
			System.out.println("Problem processing the file. Reason: " + e);
		}
	}

}
