package com.hillel.finalWork.controller;

import com.hillel.finalWork.CustomMessage;
import com.hillel.finalWork.model.Orders;
import com.hillel.finalWork.model.Status;
import com.hillel.finalWork.service.OrderService;
import com.hillel.finalWork.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class MainController {

	public static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	OrderService orderService;

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexPage() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}

	@RequestMapping(value = "/breakpoint", method = RequestMethod.GET)
	public String breakpointPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "breakpoint";
	}

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String managerPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "manager";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "user";
	}

	//	@PreAuthorize("#oauth2.hasScope('ADMIN')")
	@RequestMapping(value = "/getMyOrders",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orders>> getMyOrders(@RequestParam("number") String number) {
    	String userName = userService.getUserData().getUsername();
        int id = userService.findByName(userName).getId();
		List<Orders> allPurchases = orderService.findAll();
		List<Orders> ordersByUser = allPurchases.stream().filter(s -> s.getUser().getId() == id).limit(Long.parseLong(number)).peek(p -> p.setUser(null)).collect(Collectors.toList());
		if (ordersByUser == null) {
			logger.error("Unable to find Orders");
			return new ResponseEntity(new CustomMessage("Unable to find Orders"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ordersByUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/topOrders",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> top() {
		List<Object> top = orderService.findTop();
		if (top == null) {
			logger.error("Unable to find Orders");
			return new ResponseEntity("Unable to find Orders", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(top, HttpStatus.OK);
	}

	@RequestMapping(value = "/findOne",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(@RequestParam("id") String id) {
		Orders one = orderService.findOne(Integer.parseInt(id));
		if (one == null) {
			logger.error("Unable to find Orders");
			return new ResponseEntity("Unable to find Orders", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(one, HttpStatus.OK);
	}

	@RequestMapping(value = "/findAll",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orders>> findAll() {
		List<Orders> all = orderService.findAll();
		if (all == null) {
			logger.error("Unable to find Orders");
			return new ResponseEntity("Unable to find Orders", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

	@RequestMapping(value = "/createOrder",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createOrder(@RequestParam("date") Date date,
											  @RequestParam("status") Status status, @RequestParam("user") int userId) {
		Orders newOrder = new Orders();
		newOrder.setId(0);
		newOrder.setCreated(date);
		newOrder.setStatus(status);
		newOrder.setUser(userService.findByID(userId));
		boolean one = orderService.update(newOrder);
		if (!one) {
			logger.error("Unable to create Order");
			return new ResponseEntity(new CustomMessage("Unable to update Order"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new CustomMessage("Order create successfully"), HttpStatus.OK);
	}

	@RequestMapping(value = "/updateOrders",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateOne(@RequestParam("id") int id, @RequestParam("date") Date date,
											@RequestParam("status") Status status, @RequestParam("user") int userId) {
		Orders newOrder = new Orders();
		newOrder.setId(id);
		newOrder.setCreated(date);
		newOrder.setStatus(status);
		newOrder.setUser(userService.findByID(userId));
		boolean one = orderService.update(newOrder);
		if (!one) {
			logger.error("Unable to update Order");
			return new ResponseEntity("Unable to update Order",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new CustomMessage("Order update successfully"), HttpStatus.OK);
	}

	@RequestMapping( value = "/deleteOrders", method = RequestMethod.POST)
	public ResponseEntity<CustomMessage> deleteOrder(@RequestParam("number") String number) {
		Orders order = orderService.findOne(Integer.parseInt(number));
		if (order == null) {
			logger.error("Unable to deleteEntity. Order with id {} not found.", number);
			return new ResponseEntity(new CustomMessage("Unable to deleteEntity. Order with id " + number + " not found."),
					HttpStatus.NOT_FOUND);
		}
		orderService.delete(order);
		return new ResponseEntity<>(new CustomMessage("Order with id " + number + " deleted."), HttpStatus.OK);
	}

//	@RequestMapping(value = "/")
//	public void getMainPage3(HttpServletResponse response) throws IOException {
//		response.sendRedirect("/userPage.html");
//	}

	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}