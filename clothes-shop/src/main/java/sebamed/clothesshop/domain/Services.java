package sebamed.clothesshop.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Services {

	@Id
	private Long id;
	private String title;
	private String text;
	private String icon;

	public Services() {
		
	}
	
	public Services(Long id, String title, String text, String icon) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.icon = icon;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
