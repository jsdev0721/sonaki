package com.example.sonaki.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
	
	private final ImageService imageService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("upload") MultipartFile file) {
	    try {
	    	System.out.println("테스트테스트");
	    	
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        Path uploadPath = Paths.get("uploads");
	        Files.createDirectories(uploadPath);  // 여기서 IOException이 발생할 수 있습니다.
	        
	        Path filePath = uploadPath.resolve(fileName);
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	        String fileUrl = "/uploads/" + fileName;
	        
	        return ResponseEntity.ok(Map.of("url", fileUrl));
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return ResponseEntity.status(500).body("파일 업로드 중 오류가 발생했습니다.");
	    }
	}
	
}
