package com.spring.template.controller;

import com.spring.template.model.Order;
import com.spring.template.model.OrderDetail;
import com.spring.template.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/orders")
public class OrderController {

	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private OrderService orderService;

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String showAllUsers(Model model) throws Exception{

		logger.debug("showAllOrders()");
		model.addAttribute("orders", orderService.getAllOrder());
		return "orderList";

	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String deleteOrder(@PathVariable("id") int id, Model model )  throws  Exception{

		logger.debug("deleteOrder() : {}", id);

		orderService.deleteOrder(id);

		model.addAttribute("css", "success");
		model.addAttribute("msg", "Order is deleted!");

		return "redirect:/list";

	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String showOrder(@PathVariable("id") int id, Model model) throws  Exception{

		logger.debug("showOrder() id: {}", id);

		Order order = orderService.findOrderById(id);
		if (order == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Order not found");

			return "redirect:/list";

		}else{
			List<OrderDetail> orderDetails = orderService.getAllOrderDetailByOrder(order);
			model.addAttribute("order", order);
			model.addAttribute("orderDetails", orderDetails);
		}


		return "show";

	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showOrderForm(@PathVariable("id") int id, Model model) throws Exception {

        logger.debug("showUpdateOrderForm() : {}", id);

        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);

        return "orderForm";
    }

	@RequestMapping(value = "/ordersAction", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("order") @Valid Order order,
								   ModelMap model, BindingResult result, final RedirectAttributes redirectAttributes) throws Exception {

		logger.debug("saveOrUpdateOrder() : {}", order);

		if (result.hasErrors()) {
			return "orderForm";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			if(order.isNew()){
				redirectAttributes.addFlashAttribute("msg", "Order added successfully!");
			}else{
				redirectAttributes.addFlashAttribute("msg", "Order updated successfully!");
			}
			
			orderService.saveOrUpdateOrder(order);
			
			return "redirect:/list";
		}

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showAddOrderForm(Model model) {

		logger.debug("showAddOrderForm()");

		Order order = new Order();


		model.addAttribute("order", order);

		return "orderForm";

	}
}