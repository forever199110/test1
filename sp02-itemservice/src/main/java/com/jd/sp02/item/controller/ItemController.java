package com.jd.sp02.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jd.sp01.pojo.Item;
import com.jd.sp01.service.ItemService;
import com.jd.web.utils.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Controller
@ResponseBody
@Slf4j
public class ItemController {
	@Autowired
	private ItemService itemService;
	@Value("${server.port}")
	private int port;
	@GetMapping("/{orderId}")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId) {
		log.info("server.port="+port+", orderId="+orderId);
		
		List<Item> items = itemService.getItems(orderId);
		return JsonResult.ok(items).msg("port="+port);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/decreaseNumber")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		itemService.decreaseNumbers(items);
		return JsonResult.ok();
	}
}
