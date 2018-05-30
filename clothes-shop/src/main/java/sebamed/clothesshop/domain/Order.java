package sebamed.clothesshop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description")
	private String description;

	@JoinTable(name = "order_products")
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Product> products;

	@ManyToOne(optional = true)
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	@Column(name = "delivered")
	private boolean delivered;

	public Order() {
		super();
		this.products = new ArrayList<Product>();
		this.delivered = false;
	}

	public Order(Long id, String description, List<Product> products, User user) {
		super();
		this.products = new ArrayList<Product>();
		this.id = id;
		this.description = description;
		this.products = products;
		this.user = user;
		this.delivered = false;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> prooducts) {
		this.products = prooducts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", description=" + description + ", prooducts=" + products + ", user=" + user + "]";
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
}