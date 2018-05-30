package sebamed.clothesshop.dto;

import java.util.ArrayList;
import java.util.List;

import sebamed.clothesshop.domain.Order;
import sebamed.clothesshop.domain.Product;
import sebamed.clothesshop.domain.User;

public class OrderDTO {

	private Long id;
	private String description;
	private User user;
	private List<Product> products;
	private boolean delivered;

	public OrderDTO() {
		super();
		this.products = new ArrayList<Product>();
	}

	public OrderDTO(Long id, String description, User user, List<Product> products, boolean delivered) {
		super();
		this.products = new ArrayList<Product>();
		this.id = id;
		this.description = description;
		this.user = user;
		this.products = products;
		this.delivered = delivered;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

}
