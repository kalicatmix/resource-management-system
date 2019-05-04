package com.manager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manager.api.jsonmodel.ErrorModel;
import com.manager.api.jsonmodel.SuccessModel;
import com.manager.model.Item;
import com.manager.service.ItemService;

@RestController
@RequestMapping("api/item")
public class ItemApi {
	@Autowired
	ItemService itemService;
    @GetMapping("get/{type}")
	public Object get(@RequestParam("token")String token,@PathVariable("type")String type) {
    	Item item=itemService.getByUser(type);
    	if(item!=null)
    	return item;
    	return ErrorModel.getDefault();
	}
    @PostMapping("change/{type}/{number}")
    public Object change(@RequestParam(value="token",required=false)String token,@PathVariable("type")String type,@PathVariable("number")String number) {
     try {
      Item item=itemService.getByUser(type);
      item.setNumber(Integer.valueOf(number));
      itemService.save(item);	  
      return SuccessModel.getDefault();	  
      }catch(Exception e) {};
      return ErrorModel.getDefault();
    	
    }
/*
 * more and more
 */
}
