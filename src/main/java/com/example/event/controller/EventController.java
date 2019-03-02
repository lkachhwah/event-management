package com.example.event.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.event.pojo.Categories;
import com.example.event.pojo.Neighborhoods;
import com.example.event.service.EventService;
import com.example.event.util.Constant;

import io.swagger.annotations.Api;;

@RestController
@RequestMapping(Constant.API_ENDPOINT)
@Api(value = Constant.API_ENDPOINT, description = "Event Details", produces = "application/json")
public class EventController {

	@Autowired
	EventService eventService;

	@GetMapping("/locations")
	public ArrayList<Neighborhoods> getNeighborhoods() {
		return eventService.getNeighborhoods();
	}

	@GetMapping("/categories")
	public ArrayList<Categories> getCategories() {
		return eventService.getCategories();
	}

	@GetMapping("/events")
	public void handleSubscriptions(@RequestParam("categories") Collection<String> categories,
			@RequestParam("location") String location) throws Exception {
		List<Neighborhoods> locationList = validateLocation(location);
		List<Categories> categorieList = validateCategories(categories);
		if (categorieList.isEmpty() || locationList.isEmpty())
			throw new Exception("Please provide valid category and location name or id");
		System.out.println(location);
		categories.forEach(topic -> System.out.println(topic));
	}

	private List<Categories> validateCategories(Collection<String> categories) {
		return getCategories().stream()
				.filter(e -> (getCategories().stream()
						.filter(d -> (d.getName().equalsIgnoreCase(String.valueOf(e))
								|| d.getCategory_id().equalsIgnoreCase(String.valueOf(e))))
						.count()) < 1)
				.collect(Collectors.toList());
	}

	private List<Neighborhoods> validateLocation(String location) {
		return getNeighborhoods().stream().filter(
				c -> (c.getName().equalsIgnoreCase(location) || c.getNeighborhood_id().equalsIgnoreCase(location)))
				.collect(Collectors.toList());
	}
}
