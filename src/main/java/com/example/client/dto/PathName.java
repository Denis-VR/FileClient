package com.example.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PathName {

	@NotBlank(message = " Не должен быть пыстым или содержать пробелы")
	String name;
}
