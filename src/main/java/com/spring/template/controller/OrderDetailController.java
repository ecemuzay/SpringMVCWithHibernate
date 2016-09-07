package com.spring.template.controller;

import com.spring.template.model.Order;
import com.spring.template.model.OrderDetail;
import com.spring.template.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class OrderDetailController {

	private final Logger logger = LoggerFactory.getLogger(OrderDetailController.class);

	private OrderService orderService;

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	@RequestMapping(value = "/orderDetail/{oid}/{id}/delete", method = RequestMethod.GET)
	public String deleteOrder(@PathVariable("oid") int oid,@PathVariable("id") int id, Model model )  throws  Exception{

		logger.debug("deleteOrderDetail() : {}", id);

		orderService.deleteOrderDetail(id);

		model.addAttribute("css", "success");
		model.addAttribute("msg", "Order Detail is deleted!");

		return "redirect:/orders/"+ oid +"/detail";

	}

	@RequestMapping(value = "/orderDetail/{id}/update", method = RequestMethod.GET)
	public String showOrderForm(@PathVariable("id") int id, Model model) throws Exception {

        logger.debug("showUpdateOrderForm() : {}", id);

        OrderDetail orderDetail = orderService.findOrderDetailById(id);
        model.addAttribute("orderDetail", orderDetail);

        return "orderDetailForm";
    }

	@RequestMapping(value = "/orderDetailAction", method = RequestMethod.POST)
	public String saveOrUpdateOrderDetail(@ModelAttribute("orderDetail") @Validated OrderDetail orderDetail,
                                   BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws Exception {

		logger.debug("saveOrUpdateOrderDetail() : {}", orderDetail);

		if (result.hasErrors()) {
			return "orderDetailForm";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			if(orderDetail.isNew()){
				redirectAttributes.addFlashAttribute("msg", "Order Detail added successfully!");
			}else{
				redirectAttributes.addFlashAttribute("msg", "Order Detail updated successfully!");
			}
			
			orderService.saveOrUpdateOrderDetail(orderDetail);
			
			return "redirect:/orders/"+ orderDetail.getOrder().getId()+"/detail";
		}

	}

	@RequestMapping(value = "/orderDetail/{id}/add", method = RequestMethod.GET)
	public String showAddOrderDetailForm(@PathVariable("id") int id,Model model) {

		logger.debug("showAddOrderDetailForm()");

		OrderDetail orderDetail = new OrderDetail("",new Order());
		orderDetail.getOrder().setId(id);
		model.addAttribute("orderDetail", orderDetail);

		return "orderDetailForm";
	}
}