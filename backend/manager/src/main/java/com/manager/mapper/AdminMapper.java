package com.manager.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.manager.model.Admin;

public interface AdminMapper {
	@Select("select * from admin where user=#{user} and pwd=#{pwd}")
	 Admin getByName(@Param("user")String user,@Param("pwd")String pwd);
	 @Delete("delete from admin where user=#{user} and pwd=#{pwd}")
	 void delete(@Param("user")String user,@Param("pwd")String pwd);
	 @Update("update admin set pwd=#{pwd},changeword=${changeword}, pwdmd5=#{pwdmd5} where user=#{user}")
	 void save(@Param("user")String user,@Param("pwd")String pwd,@Param("changeword")int changeword,@Param("pwdmd5")String pwdmd5);
	 @Insert("insert into admin(user,pwd,changeword,pwdmd5)values(#{user},#{pwd},#{changeword},#{pwdmd5})")
	 void insert(@Param("user")String user,@Param("pwd")String pwd,@Param("changeword")int changeword,@Param("pwdmd5")String pwdmd5);
}
