package com.example.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathDto {
	String date_created;
	String path;
	List<SimpleFileDto> nestedFiles;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SimpleFileDto {
		FileType fileType;
		String name;
		long size;
	}
}
