package sebamed.clothesshop.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

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

import com.google.common.collect.Lists;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import sebamed.clothesshop.domain.Order;
import sebamed.clothesshop.domain.User;
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

	@Autowired
	EmailService emailService;

	@GetMapping("/")
	public ResponseEntity<List<OrderDTO>> handleGetAllOrders() {
		List<OrderDTO> ordersDto = new ArrayList<OrderDTO>();
		for (Order o : this.orderService.findAll()) {
			ordersDto.add(new OrderDTO(o.getId(), o.getDescription(), o.getUser(), o.getProducts(), o.isDelivered(),
					o.getCheckout()));
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
			return new ResponseEntity<OrderDTO>(new OrderDTO(o.getId(), o.getDescription(), o.getUser(),
					o.getProducts(), o.isDelivered(), o.getCheckout()), HttpStatus.OK);
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
	public ResponseEntity<OrderDTO> handleUpdateOrder(@RequestBody OrderDTO orderDto) {
		Order o = this.orderService.findOneById(orderDto.getId());
		if (o != null) {
			o.setDelivered(orderDto.getDelivered());
			o.setDescription(orderDto.getDescription());
			o.setProducts(orderDto.getProducts());
			o.setCheckout(orderDto.getCheckout());
			this.orderService.save(o);
			return new ResponseEntity<OrderDTO>(orderDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("deliver")
	public ResponseEntity<OrderDTO> handleDeliver(@RequestBody OrderDTO orderDto) {
		Order o = this.orderService.findOneById(orderDto.getId());
		if (o != null) {
			o.setDelivered(true);
			this.orderService.save(o);
			User u = this.userService.findOne(orderDto.getUser().getId());
			if (u != null) {
				u.setOrder(o);
				this.userService.save(u);
			}

			return new ResponseEntity<OrderDTO>(new OrderDTO(o.getId(), o.getDescription(), o.getUser(),
					o.getProducts(), o.isDelivered(), o.getCheckout()), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("checkout")
	public ResponseEntity<OrderDTO> handleCheckout(@RequestBody OrderDTO orderDto) {
		Order o = this.orderService.findOneById(orderDto.getId());
		if (o != null) {
			o.setDescription(orderDto.getDescription());
			o.setCheckout(true);
			this.orderService.save(o);
			User u = this.userService.findOne(orderDto.getUser().getId());
			if (u != null) {
				u.setOrder(this.orderService.createNew(u));
				this.userService.save(u);
			}

			Email email;
			try {
				email = DefaultEmail.builder()
						.from(new InternetAddress("spring.angular123", "Spring Boot Angular App"))
						.to(Lists.newArrayList(new InternetAddress(o.getUser().getEmail(), o.getUser().getUsername())))
						.subject("Your order is ready!")
						.body(String.format("Dear " + o.getUser().getFirstName() + ", your order is ready. " + o.toString()))
						.encoding("UTF-8").build();
				this.emailService.send(email);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}


			return new ResponseEntity<OrderDTO>(new OrderDTO(o.getId(), o.getDescription(), o.getUser(),
					o.getProducts(), o.isDelivered(), o.getCheckout()), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
