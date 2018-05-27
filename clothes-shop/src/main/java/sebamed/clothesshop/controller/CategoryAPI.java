package sebamed.clothesshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sebamed.clothesshop.domain.Category;
import sebamed.clothesshop.dto.CategoryDTO;
import sebamed.clothesshop.service.CategoryService;

@RestController
@RequestMapping("api/categories")
@CrossOrigin("http://localhost:4200")
public class CategoryAPI {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> handleGetOneCategory(@PathVariable("id") Long id){
		Category c = this.categoryService.findOneById(id);
		if(c != null) {
			return new ResponseEntity<CategoryDTO>(new CategoryDTO(c.getId(), c.getName()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<CategoryDTO> handleAddCategory(@RequestBody CategoryDTO categoryDto){
		Category c = new Category();
		c.setName(categoryDto.getName());
		this.categoryService.save(c);
		return new ResponseEntity<CategoryDTO>(new CategoryDTO(c.getId(), c.getName()), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> handleGetAllCategories(){
		List<CategoryDTO> cDtoList = new ArrayList<CategoryDTO>();
		for(Category c : this.categoryService.findAll()) {
			cDtoList.add(new CategoryDTO(c.getId(), c.getName()));
		}
		
		return new ResponseEntity<List<CategoryDTO>>(cDtoList, HttpStatus.OK);
	}

}
