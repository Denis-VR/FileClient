package com.example.client.controller;

import com.example.client.client.ClientService;
import com.example.client.dto.PathName;
import com.example.client.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final FileService fileService;
	private final ClientService clientService;

	@GetMapping({"/", "/index"})
	public String index(Model model) {
		model.addAttribute("path_name", new PathName());
		model.addAttribute("paths", clientService.getPaths());
		return "index";
	}

	@GetMapping("/fileList")
	public String getFilesForPath(String path, Model model) {
		model.addAttribute("paths", clientService.getFilesForPath(path));
		return "fileList";
	}

	@PostMapping("/path")
	public String savePath(@Valid @ModelAttribute("path_name") PathName path, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("paths", clientService.getPaths());
			return "index";
		} else {
			fileService.generateNewPathDto(path);
		}
		return "redirect:/";
	}

}