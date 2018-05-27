package sebamed.clothesshop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

	@OneToOne
	@JoinColumn(name = "user_id")
	private User admin;

	@ManyToOne(optional = true)
	@JoinColumn(name = "category_id", nullable = true)
	private Category category;

	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id", nullable = true)
	private Image image;

	@Column(name = "is_public")
	private boolean isPublic;

	@Column(name = "description")
	private String description;

	public Product() {

	}

	public Product(Long id, String title, int priceMain, int priceDecimal, String currency, int discount, User admin,
			Category category, Image image, boolean isPublic, String description) {
		super();
		this.id = id;
		this.title = title;
		this.priceMain = priceMain;
		this.priceDecimal = priceDecimal;
		this.currency = currency;
		this.discount = discount;
		this.admin = admin;
		this.category = category;
		this.image = image;
		this.isPublic = isPublic;
		this.description = description;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
