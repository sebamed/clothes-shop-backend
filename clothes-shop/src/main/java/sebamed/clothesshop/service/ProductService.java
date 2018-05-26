package sebamed.clothesshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sebamed.clothesshop.domain.Image;
import sebamed.clothesshop.domain.Product;
import sebamed.clothesshop.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ImageStorageService imageStorageService;

	public Product save(Product product) {
		return this.productRepository.save(product);
	}
	
	public Product findOne(Long id) {
		return this.productRepository.findOneById(id);
	}
	
	public Product addImages(Product product, MultipartFile file) {
		this.imageStorageService.store(file);
		Image image = new Image();
		image.setUrl(file.getOriginalFilename());
		product.setImage(image);
		return product;
	}

}
