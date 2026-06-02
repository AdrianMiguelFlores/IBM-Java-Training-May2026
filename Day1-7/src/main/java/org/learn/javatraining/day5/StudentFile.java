package org.learn.javatraining.day5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.Stream;

public class StudentFile {

	public static void main(String[] args) {
		Path csvPath = Path.of("student.csv");
		Path jsonPath = Path.of("student.json");
		try (Stream<String> stream = Files.lines(csvPath); BufferedWriter bw = Files.newBufferedWriter(jsonPath)) {
			bw.write("[");
			bw.newLine();

			Iterator<String> it = stream.iterator();
			String[] header = it.next().split(",");
			String id = header[0].trim();
			String name = header[1].trim();
			String course = header[2].trim();

			while (it.hasNext()) {
				String[] lines = it.next().split(",");
				try {
					bw.write(" {");
					bw.newLine();
					bw.write("  \"" + id + "\":" + "\"" + lines[0] + "\",");
					bw.newLine();
					bw.write("  \"" + name + "\":" + lines[1] + ",");
					bw.newLine();
					bw.write("  \"" + course + "\":" + lines[2]);
					bw.newLine();
					if (it.hasNext()) {
						bw.write(" },");
					} else {
						bw.write(" }");
					}
					bw.newLine();
				} catch (IOException e) {
					System.out.println("Cannot write to a file " + jsonPath + ". Reason: " + e);
				}
			}

			bw.write("]");
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Cannot process file " + csvPath + ". Reason: " + e);
		}

		try {
			String jsonContent = Files.readString(jsonPath);
			System.out.println(jsonContent);
		} catch (IOException e) {
			System.out.println("Cannot read json file " + jsonPath + ". Reason: " + e);
		}
	}

}
