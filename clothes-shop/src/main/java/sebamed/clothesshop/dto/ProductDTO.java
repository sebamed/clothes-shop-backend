package sebamed.clothesshop.dto;

import java.util.List;

import sebamed.clothesshop.domain.Image;
import sebamed.clothesshop.domain.User;

public class ProductDTO {

	private Long id;
	private String title;

	public ProductDTO() {

	}

	public ProductDTO(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
