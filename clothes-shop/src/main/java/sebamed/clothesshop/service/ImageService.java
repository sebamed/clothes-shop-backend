package sebamed.clothesshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.domain.Image;
import sebamed.clothesshop.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	ImageRepository imageRepository;

	public Image save(Image image) {
		return this.imageRepository.save(image);
	}

	public Image findOne(Long id) {
		return this.imageRepository.findOneById(id);
	}

}
