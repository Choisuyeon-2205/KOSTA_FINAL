package com.kosta.finalProject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.finalProject.services.S3Uploader;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class S3Controller {
	private final S3Uploader s3Uploader;

	@GetMapping("/test")
	public String index() {
		return "exerciseRecord/test";
	}

	// sessionID로 user_id를 받아온 뒤 exerciseRecord/user별 폴더를 만들고 이미지 업로드 시 파일이름을 실제 이름이 아니라 날짜로 하면 전체 조회 가능
	   @PostMapping("/upload")
	   @ResponseBody
	   public String upload(@RequestParam("data") MultipartFile multipartFile, @RequestParam("path") String path) throws IOException {
	      return s3Uploader.upload(multipartFile, path);
	   }
}
