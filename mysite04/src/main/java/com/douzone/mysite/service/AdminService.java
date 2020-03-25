package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.AdminRepository;
import com.douzone.mysite.vo.AdminVO;

@Service
public class AdminService {
	private static final String SAVE_PATH="/mysite-uploads";
	private static final String URL="/images/";
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	
	public Boolean modifyContents(AdminVO adminVO) {
		return adminRepository.update(adminVO) == 1;
	}


	public String restore(MultipartFile multipartFile) {
		
		String url = null;
		if(multipartFile.isEmpty()) {
			return "1111";
		}
		
		try {
			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.')+1);
			
			String saveFileName = generateSaveFileName(extName);

			writeFile(multipartFile,saveFileName);
			url = URL + saveFileName;
		}catch(IOException ex) {
			throw new RuntimeException("file upload error:"+ex);
		}
		
		return url;	
	}
	
	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("."+extName);
		return fileName;
	}

	private boolean writeFile(MultipartFile multipartFile,String saveFileName) throws IOException{
		boolean result = false;
		byte[] fileData = multipartFile.getBytes();
		OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
		os.write(fileData);
		os.close();
		return result;
	}


	public AdminVO getContents() {
		AdminVO adminVO = adminRepository.findAll();
		return adminVO;
		
	}

}
