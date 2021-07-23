package com.example.client.client;

import com.example.client.dto.FileDto;
import com.example.client.dto.PathDto;
import com.example.client.dto.PathResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

	@Value("${server.url}")
	private String SERVER_URL;
	private final RestTemplate restTemplate;

	public void sendNewPath(PathDto path) {
		restTemplate.postForObject(SERVER_URL + "/path", path, PathDto.class);
	}

	public List<PathResponse> getPaths() {
		return restTemplate.exchange(
				SERVER_URL + "/path",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<PathResponse>>() {
				}
		).getBody();
	}

	public List<FileDto> getFilesForPath(String path) {
		return restTemplate.exchange(
				SERVER_URL + "/file?path=" + path,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<FileDto>>() {
				}
		).getBody();
	}

	public Boolean checkFileExist(String path) {
		return restTemplate.exchange(
				SERVER_URL + "/fileExist?path=" + path,
				HttpMethod.GET,
				null,
				Boolean.class
		).getBody();
	}
}
