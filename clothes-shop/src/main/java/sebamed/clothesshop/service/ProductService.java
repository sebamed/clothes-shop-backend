package sebamed.clothesshop.service;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	public Product findOneById(Long id) {
		return this.productRepository.findOneById(id);
	}

	public void remove(Long id) {
		Product p = this.productRepository.findOneById(id);
		if(p != null ) {
			Image i = this.imageService.findOne(p.getImage().getId());
			if(i != null) {
				this.imageService.remove(i.getId());
			}
			this.productRepository.delete(p);
		}
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
		product.setCategory(productDto.getCategory());

		this.imageStorageService.store(file);
		Image image = new Image();
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (null != requestAttributes && requestAttributes instanceof ServletRequestAttributes) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			String baseUrl = String.format("%s://%s:%d/product-images/", request.getScheme(), request.getServerName(),
					request.getServerPort());
			image.setUrl(baseUrl + file.getName());
		} else {
			image.setUrl(file.getName());
		}
		this.imageService.save(image);

		product.setImage(image);

		return product;
	}

}
