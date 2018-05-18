package sebamed.clothesshop.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	private Long id;
	
	private String title;
	
}
