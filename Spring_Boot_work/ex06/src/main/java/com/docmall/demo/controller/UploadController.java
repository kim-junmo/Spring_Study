package com.docmall.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.docmall.demo.dto.AttachFileDTO;
import com.docmall.demo.service.UploadService;
import com.docmall.demo.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/upload/*")
@RequiredArgsConstructor
@Controller
public class UploadController {
	
	private final UploadService uploadService;
	private FileUtils fileUtils;
	
	
	@Value("${org.docmall.upload.path}") //C:\\dev\\upload\\pds
	private String uploadFolder;
	
	//업로드 방식 1)<form>태그를 이용한 방식.
	@GetMapping("uploadForm")
	public void uploadForm() {
		
	}
	
	//com.docmall.demo.config 패키지의 MultipartConfig클래스안에 multipartResolver bean이 업로드 파일을 참조하여
	//multipartFile[] uploadFile 이 파라미터로 사용하게 해준다.
	//multipartFile: 클라이언트쪽에서 업로드 된 파일 정보를 지니고 있다.
	@PostMapping("uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile) {
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("----------------------");
			log.info("파일이름: " + multipartFile.getOriginalFilename());
			log.info("파일크기: " + multipartFile.getSize());
			
			//new File(파일 또는 폴더 경로) 
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	//업로드 방식 2)ajax를 이용한 방식.
	@GetMapping("uploadAjax")
	public void uploadAjax() {
		
	}
	//ajax요청으로 파일 업로드 작업을 진행하고, 업로드 된 파일목록정보를 리턴해주는 기능.
	//리턴값은 <List<AttachFileDTO>> -> JSON으로 변환되어 클라이언트로 전송된다.(jackson-databind라이브러리 작업)
	@ResponseBody //ajax요청받는 매핑주소는 이 어노테이션을 반드식 달아야 한다.
	@PostMapping(value = "uploadAjaxAction", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		
		//전체적인 리턴타입 : ResponseEntity<List<AttachFileDTO>> entity = null;
		//실질적인 리턴타입 :  <List<AttachFileDTO>>
		ResponseEntity<List<AttachFileDTO>> entity = null;
		
		//업로드한 파일 정보 목록 =list
		List<AttachFileDTO> list = new ArrayList<>();
		
		String uploadDateFolder = fileUtils.getDateFolder(); //날짜 폴더를 얻어옴.
		
		for(MultipartFile multipartFile : uploadFile) {
			AttachFileDTO attachFileDTO = new AttachFileDTO();
			
			//1) 클라이언트 파일 이름
			String clientFileName = multipartFile.getOriginalFilename();
			attachFileDTO.setFileName(clientFileName);
			
			//2)실제 업로드한 파일명
			attachFileDTO.setUuid(fileUtils.uploadFile(uploadFolder, uploadDateFolder, multipartFile));
			
			//3)날짜폴더명
			attachFileDTO.setUploadPath(uploadDateFolder);
			
			
			File saveFile = new File(uploadFolder, clientFileName);
			
			if(fileUtils.checkImageType(saveFile)) {
				//이미지 파일여부
				attachFileDTO.setImage(true);
			}
			list.add(attachFileDTO);
		}
		
		entity = new ResponseEntity<List<AttachFileDTO>>(list, HttpStatus.OK);
		
		return entity;
	}
	
	//<img src="매핑주소">
	@GetMapping("display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		
		ResponseEntity<byte[]> entity = null;
					
		try {
			entity = fileUtils.getFile(uploadFolder, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entity;
	}
	
	
	//파일 삭제
	@PostMapping(value = "daleteFile")
	@ResponseBody
	public ResponseEntity<String> daleteFile(String dateFolderName, String fileName, String type) {
		
		log.info("fileName: " + fileName);
		log.info("type: " + type);
		
		ResponseEntity<String> entity = null;
		
		fileUtils.delete(uploadFolder, fileName, type);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		return entity;
	}
	
}
