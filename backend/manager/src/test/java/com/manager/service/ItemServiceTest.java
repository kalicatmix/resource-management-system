package com.manager.service;

import java.util.ArrayList;

import org.junit.Test;

import com.manager.ApplicationContextRunner;
import com.manager.model.Item;
import com.manager.model.Type;

public class ItemServiceTest extends ApplicationContextRunner {

	@Test
	public void testSave() {
	ItemService service=context.getBean(ItemService.class);
	ArrayList<Type> types=service.getAllTypes();
	ArrayList<Item> items=service.getAllItems();
	for(Type type:types) {
		System.out.println(type.getType());
	}
	for(Item item:items) {
		System.out.println(item.getNumber());
	}
	}
}
