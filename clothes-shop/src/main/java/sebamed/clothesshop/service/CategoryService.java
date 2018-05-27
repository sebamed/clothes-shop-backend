package sebamed.clothesshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.domain.Category;
import sebamed.clothesshop.domain.Product;
import sebamed.clothesshop.repository.CategoryRepository;
import sebamed.clothesshop.repository.ProductRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	public Category save(Category c) {
		return this.categoryRepository.save(c);
	}

	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public Category findOneById(Long id) {
		return this.categoryRepository.findOneById(id);
	}

	public void remove(Long id) {
		Category c = this.categoryRepository.findOneById(id);
		if (c != null) {
			for (Product p : this.productRepository.findAll()) {
				if (p.getCategory().equals(p)) {
					p.setCategory(null);
				}
			}
			this.categoryRepository.delete(c);
		}
	}

}
