package sebamed.clothesshop.service;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sebamed.clothesshop.domain.Image;
import sebamed.clothesshop.dto.ImageDTO;
import sebamed.clothesshop.repository.ImageRepository;

@Service
public class ImageStorageService {

	private final Path rootLocation = Paths.get("product-images");

	public void store(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
			
		} catch (Exception e) {
			throw new RuntimeException("Fail with upload!");
		}
	}
	
	public Resource loadFile(ImageDTO imageDto) {
		try {
			Path file = rootLocation.resolve(imageDto.getName());
			Resource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new RuntimeException("Fail with loading file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Fail with loading file!");
		}
	}
	
	public void init() {
		try {
			if(Files.exists(this.rootLocation)) {
				return;
			} else {
				Files.createDirectory(this.rootLocation);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
}
