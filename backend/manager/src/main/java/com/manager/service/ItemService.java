package com.manager.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manager.mapper.ItemMapper;
import com.manager.model.Item;
import com.manager.model.Type;

@Service
@Transactional
public class ItemService{
    @Autowired
    ItemMapper itemMapper;
	public boolean delete(Item t) {
		itemMapper.delete(t.getType());
		return true;
	}

	public boolean save(Item t) {
		itemMapper.add(t.getType(),t.getNumber());
		return true;
	}

	public boolean add(Item t) {
		itemMapper.insert(t.getType());
		return true;
	}

	public Item getByUser(String type) {
		return itemMapper.getByName(type);
	}
	public ArrayList<Type>getAllTypes(){
		return itemMapper.getAllTypes();
	}
    public ArrayList<Item>getAllItems(){
    	return itemMapper.getAllItems();
    }
}
