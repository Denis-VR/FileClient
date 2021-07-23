package com.example.client.service;

import com.example.client.client.ClientService;
import com.example.client.dto.FileType;
import com.example.client.dto.PathDto;
import com.example.client.dto.PathName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

	private final ClientService clientService;

	public void generateNewPathDto(PathName path_name) {
		log.info("Попытка просканировать путь: " + path_name.getName());
		//without it there will be problems with jsp
		String pathNormal = path_name.getName().replace("\\", "/");

		File file = new File(path_name.getName());
		if (!file.exists()) {
			log.info("Такого пути не сущетсвует");
			return;
		}

		PathDto pathDto = new PathDto(
				new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()),
				pathNormal,
				defineNestedFiles(file)
		);

		try {
			clientService.sendNewPath(pathDto);
			log.info("Файлы успешно отправлены. Дубликаты сервер не добавит в базу.");
		} catch (Exception e) {
			log.info("Ошибка отправки файлов");
			e.printStackTrace();
		}
	}

	private List<PathDto.SimpleFileDto> defineNestedFiles(File file) {
		File[] innerFiles = file.listFiles();
		if (innerFiles == null || innerFiles.length == 0) {
			return null;
		} else {
			return Arrays.stream(innerFiles)
					.map(file1 -> {
						FileType fileType = defineFileType(file1);
						return new PathDto.SimpleFileDto(
								fileType,
								file1.getName(),
								fileType == FileType.FILE ? file1.length() : FileUtils.sizeOfDirectory(file1)
						);
					}).collect(Collectors.toList());
		}
	}

	private FileType defineFileType(File file) {
		if (file.isDirectory()) return FileType.DIRECTORY;
		if (file.isFile()) return FileType.FILE;
		else return FileType.UNKNOWN;
	}
}
