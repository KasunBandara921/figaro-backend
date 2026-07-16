package com.figaro.salon_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class SalonManagementSystemApplication {

	public static void main(String[] args) {
		try {
			if (Files.exists(Paths.get(".env"))) {
				List<String> lines = Files.readAllLines(Paths.get(".env"));
				for (String line : lines) {
					line = line.trim();
					if (line.isEmpty() || line.startsWith("#")) {
						continue;
					}
					int eqIndex = line.indexOf('=');
					if (eqIndex > 0) {
						String key = line.substring(0, eqIndex).trim();
						String value = line.substring(eqIndex + 1).trim();
						if (value.startsWith("\"") && value.endsWith("\"")) {
							value = value.substring(1, value.length() - 1);
						} else if (value.startsWith("'") && value.endsWith("'")) {
							value = value.substring(1, value.length() - 1);
						}
						System.setProperty(key, value);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Failed to load .env file: " + e.getMessage());
		}
		SpringApplication.run(SalonManagementSystemApplication.class, args);
	}

}

