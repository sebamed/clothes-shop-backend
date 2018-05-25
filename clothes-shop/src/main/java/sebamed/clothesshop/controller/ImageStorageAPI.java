package sebamed.clothesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sebamed.clothesshop.domain.Image;
import sebamed.clothesshop.dto.ImageDTO;
import sebamed.clothesshop.service.ImageService;
import sebamed.clothesshop.service.ImageStorageService;

@RestController
@RequestMapping("/api/images")
public class ImageStorageAPI {

	@Autowired
	ImageStorageService imageStorageService;
	
	@Autowired
	ImageService imageService;
	
	@PostMapping("/upload")
	public ResponseEntity<ImageDTO> handleUploadImage(@RequestParam("file") MultipartFile file){
		try {
			this.imageStorageService.init();
			this.imageStorageService.store(file);
			
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Image image = new Image();
		image.setUrl(file.getOriginalFilename());
		this.imageService.save(image);
		return new ResponseEntity<ImageDTO>(new ImageDTO(image.getId(), image.getUrl()), HttpStatus.OK);
	}
	
}
