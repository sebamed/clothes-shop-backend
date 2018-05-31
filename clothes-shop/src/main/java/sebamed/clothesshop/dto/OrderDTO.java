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
	private boolean checkout;

	public OrderDTO() {
		super();
		this.products = new ArrayList<Product>();
	}

	public OrderDTO(Long id, String description, User user, List<Product> products, boolean delivered,
			boolean checkout) {
		super();
		this.products = new ArrayList<Product>();
		this.id = id;
		this.description = description;
		this.user = user;
		this.products = products;
		this.delivered = delivered;
		this.checkout = checkout;
	}

	public boolean getCheckout() {
		return checkout;
	}

	public void setCheckout(boolean checkout) {
		this.checkout = checkout;
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

	public boolean getDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", description=" + description + ", user=" + user + ", products=" + products
				+ ", delivered=" + delivered + "]";
	}

}
