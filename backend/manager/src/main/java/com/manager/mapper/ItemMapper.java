package com.manager.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.manager.model.Item;
import com.manager.model.Type;

public interface ItemMapper {
	@Select("select * from number where type=#{type}")
	 Item getByName(@Param("type")String type);
	 @Delete("delete from number where type=#{type}")
	 void delete(@Param("type")String type);
	 @Update("update number set number=${number} where type=#{type}")
	 void add(@Param("type")String type,@Param("number")int number);
	 @Insert("insert into type values(#{type})")
	 void insert(@Param("type")String type);
	 @Select("select * from type")
	 ArrayList<Type> getAllTypes();
	 @Select("select * from number")
	 ArrayList<Item> getAllItems();
}
