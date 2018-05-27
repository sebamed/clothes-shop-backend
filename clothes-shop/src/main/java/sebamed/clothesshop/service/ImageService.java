package sebamed.clothesshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.domain.Image;
import sebamed.clothesshop.domain.Product;
import sebamed.clothesshop.repository.ImageRepository;
import sebamed.clothesshop.repository.ProductRepository;

@Service
public class ImageService {

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ProductRepository productRepository;

	public Image save(Image image) {
		return this.imageRepository.save(image);
	}

	public Image findOne(Long id) {
		return this.imageRepository.findOneById(id);
	}
	
	public void remove(Long id) {
		Image image = this.imageRepository.findOneById(id);
		if(image != null) {
			for(Product p : this.productRepository.findAll()) {
				if(p.getImage().equals(image)) {
					p.setImage(null);
				}
			}
			this.imageRepository.delete(image);
		}		
	}

}
