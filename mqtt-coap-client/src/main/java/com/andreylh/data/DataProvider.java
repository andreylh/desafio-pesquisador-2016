package com.andreylh.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataProvider {
	
	private List<Path> paths;
	
	public DataProvider(Path path) {
		try {
			paths = Files.walk(path).filter(p -> p.toString().endsWith(".csv")).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getData(int limit) {
		List<String> data = new ArrayList<>();
		int remainingData = limit;
		for (Path p : paths) {
			try {
				long numberOfLines = Files.lines(p).count();
				List<String> lines = Files.lines(p).limit(Math.min((remainingData + 1), numberOfLines)).collect(Collectors.toList());
				lines.remove(0); // header
				data.addAll(lines);
				remainingData -= data.size();
				if (data.size() == limit) {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return data;
	}

}
