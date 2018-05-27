package sebamed.clothesshop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import sebamed.clothesshop.domain.Product;
import sebamed.clothesshop.dto.ProductDTO;
import sebamed.clothesshop.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:4200")
public class ProductAPI {

	@Autowired
	ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<Product> handleAddProduct(@RequestParam("file") MultipartFile file,
			@RequestParam("productDto") Object data) {
		try {
			File image = new File(System.currentTimeMillis() + "_" + file.getOriginalFilename());
			image.createNewFile();
			FileOutputStream fos = new FileOutputStream(image);
			fos.write(file.getBytes());
			fos.close();

			ProductDTO productDto = new ObjectMapper().readValue(data.toString(), ProductDTO.class); // kastovanje

			Product product = this.productService.addImage(productDto, image); // dodavanje slike i vracanje produkta
			this.productService.save(product);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> handleGetProduct(@PathVariable("id") Long id) {
		Product p = this.productService.findOneById(id);
		if (p != null) {
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
