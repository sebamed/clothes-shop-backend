package sebamed.clothesshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import sebamed.clothesshop.domain.Order;
import sebamed.clothesshop.dto.OrderDTO;
import sebamed.clothesshop.service.OrderService;
import sebamed.clothesshop.service.UserService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("http://localhost:4200")
public class OrderAPI {

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<OrderDTO>> handleGetAllOrders() {
		List<OrderDTO> ordersDto = new ArrayList<OrderDTO>();
		for (Order o : this.orderService.findAll()) {
			ordersDto.add(new OrderDTO(o.getId(), o.getDescription(), o.getUser(), o.getProducts(), o.isDelivered()));
		}
		if (ordersDto.size() > 0) {
			return new ResponseEntity<List<OrderDTO>>(ordersDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> handleGetOrder(@PathVariable("id") Long id) {
		Order o = this.orderService.findOneById(id);
		if (o != null) {
			return new ResponseEntity<OrderDTO>(
					new OrderDTO(o.getId(), o.getDescription(), o.getUser(), o.getProducts(), o.isDelivered()),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/add")
	public ResponseEntity<OrderDTO> handleAddOrder(@RequestBody OrderDTO orderDto) {
		Order o = new Order();
		o.setDescription(orderDto.getDescription());
		o.setUser(orderDto.getUser());
		o.setProducts(orderDto.getProducts());
		this.orderService.save(o);
		return new ResponseEntity<OrderDTO>(orderDto, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<OrderDTO> handleUpdateOrder(@RequestBody Object object) {
		System.out.println(object.toString());
		try {
			// kastovanje u OrderDTO
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			OrderDTO orderDto = mapper.readValue(mapper.writeValueAsString(object), OrderDTO.class);

			Order o = this.orderService.findOneById(orderDto.getId());
			if(o != null) {
				o.setDelivered(orderDto.getDelivered());
				o.setDescription(orderDto.getDescription());
				o.addProducts(orderDto.getProducts());
				return new ResponseEntity<OrderDTO>(orderDto, HttpStatus.OK);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
