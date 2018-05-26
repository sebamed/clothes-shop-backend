package sebamed.clothesshop.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.domain.Image;
import sebamed.clothesshop.domain.Product;
import sebamed.clothesshop.dto.ProductDTO;
import sebamed.clothesshop.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ImageStorageService imageStorageService;

	@Autowired
	ImageService imageService;
	
	public Product save(Product product) {
		return this.productRepository.save(product);
	}
	
	public Product findOne(Long id) {
		return this.productRepository.findOneById(id);
	}
	
	public Product addImage(ProductDTO productDto, File file) {
		Product product = new Product();
		product.setAdmin(productDto.getAdmin());
		product.setCurrency(productDto.getCurrency());
		product.setDescription(productDto.getDescription());
		product.setDiscount(productDto.getDiscount());
		product.setPriceDecimal(productDto.getPriceDecimal());
		product.setPriceMain(productDto.getPriceMain());
		product.setTitle(productDto.getTitle());
		product.setPublic(productDto.getIsPublic());
		
		this.imageStorageService.store(file);
		Image image = new Image();
		image.setUrl(file.getName());
		this.imageService.save(image);
		
		product.setImage(image);
		
		return product;
	}

}
