package sebamed.clothesshop.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.dto.ImageDTO;

@Service
public class ImageStorageService {

	private final Path rootLocation = Paths.get("src/main/resources/static/product-images");

	public void store(File file) {
		this.init();
		try {
			FileUtils.moveFileToDirectory(file, new File(this.rootLocation.toString()), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Resource loadFile(ImageDTO imageDto) {
		try {
			Path file = rootLocation.resolve(imageDto.getName());
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Fail with loading file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Fail with loading file!");
		}
	}

	public void init() {
		try {
			if (Files.exists(this.rootLocation)) {
				return;
			} else {
				Files.createDirectory(this.rootLocation);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
}
