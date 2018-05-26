package sebamed.clothesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sebamed.clothesshop.domain.Product;
import sebamed.clothesshop.dto.ProductDTO;
import sebamed.clothesshop.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

	@Autowired
	ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<Product> handleAddProduct(@RequestParam("file") MultipartFile file,
														@RequestParam("product") String text){
		Product product = new Product();
	
			product.setTitle(text);
			product = this.productService.addImages(product, file);
			

		this.productService.save(product);
		return null;
	}

}
