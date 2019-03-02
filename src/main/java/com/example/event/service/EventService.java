package com.example.event.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.event.main.EventfulApplication;
import com.example.event.pojo.Categories;
import com.example.event.pojo.Neighborhoods;

@Service
public class EventService {

	public ArrayList<Neighborhoods> getNeighborhoods() {
		return EventfulApplication.data.getSearch().getTabular().getFacets().getNeighborhoods();
	}

	public ArrayList<Categories> getCategories() {
		return EventfulApplication.data.getSearch().getTabular().getFacets().getCategories();
	}

}
