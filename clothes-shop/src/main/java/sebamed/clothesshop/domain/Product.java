package sebamed.clothesshop.domain;

import java.util.List;

import javax.persistence.Column;
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

	@Column(name = "is_public")
	private boolean isPublic;

	@Column(name = "description")
	private String description;
	
	

}
