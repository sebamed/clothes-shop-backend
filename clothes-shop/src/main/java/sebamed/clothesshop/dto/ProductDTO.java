package sebamed.clothesshop.dto;

import java.util.List;

import sebamed.clothesshop.domain.Image;
import sebamed.clothesshop.domain.User;

public class ProductDTO {

	private String title;
	private int priceMain;
	private int priceDecimal;
	private String currency;
	private int discount;
	private User admin;
	private boolean isPublic;
	private String description;

	public ProductDTO() {
		
	}
	
	public ProductDTO(String title, int priceMain, int priceDecimal, String currency, int discount, User admin,
			boolean isPublic, String description) {
		super();
		this.title = title;
		this.priceMain = priceMain;
		this.priceDecimal = priceDecimal;
		this.currency = currency;
		this.discount = discount;
		this.admin = admin;
		this.isPublic = isPublic;
		this.description = description;
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

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductDTO [title=" + title + ", priceMain=" + priceMain + ", priceDecimal=" + priceDecimal
				+ ", currency=" + currency + ", discount=" + discount + ", admin=" + admin + ", isPublic=" + isPublic
				+ ", description=" + description + "]";
	}	
	
	
}
