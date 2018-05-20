package sebamed.clothesshop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private int priceMain;

	private int priceDecimal;

	private String currency;

	private int discount;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User admin;

	@OneToMany
	@JoinColumn(name = "product_images")
	private List<Image> images;

	public Product(Long id, String title, int priceMain, int priceDecimal, String currency, int discount, User admin,
			List<Image> images) {
		super();
		this.id = id;
		this.title = title;
		this.priceMain = priceMain;
		this.priceDecimal = priceDecimal;
		this.currency = currency;
		this.discount = discount;
		this.admin = admin;
		this.images = images;
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

	public int getPriceMain() {
		return priceMain;
	}

	public void setPriceMain(int priceMain) {
		this.priceMain = priceMain;
	}

	public int getPriceDecimal() {
		return priceDecimal;
	}

	public void setPriceDecimal(int priceDecimal) {
		this.priceDecimal = priceDecimal;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

}
