package com.manager.mapper;

import java.awt.List;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;

import com.manager.model.Type;
import com.manager.model.User;


public interface UserMapper {
 @Select("select * from user where user=#{user} and pwd=#{pwd}")
 User getByName(@Param("user")String user,@Param("pwd")String pwd);
 @Delete("delete from user where user=#{user} and pwd=#{pwd} and changeword=#{changeword}")
 void delete(@Param("user")String user,@Param("pwd")String pwd,@Param("changeword")int changeword);
 @Update("update user set pwd=#{pwd},changeword=${changeword}, pwdmd5=#{pwdmd5} where user=#{user}")
 void save(@Param("user")String user,@Param("pwd")String pwd,@Param("changeword")int changeword,@Param("pwdmd5")String pwdmd5);
 @Insert("insert into user(user,pwd,changeword,pwdmd5)values(#{user},#{pwd},#{changeword},#{pwdmd5})")
 void insert(@Param("user")String user,@Param("pwd")String pwd,@Param("changeword")int changeword,@Param("pwdmd5")String pwdmd5);
}
