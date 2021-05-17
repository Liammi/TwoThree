package com.example.video.dao;

import com.example.video.pojo.Admin;
import com.example.video.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminDao {

    @Insert("Insert into admin(admin_name,password) values (#{adminName},#{password})")
    void saveAdmin(Admin admin);

    @Select("select * from admin where admin_name = #{adminName} and password = #{password}")
    @ResultType(Admin.class)
    Admin getAdminByNameAndPassword(String adminName,String password);

    @Select("select * from admin")
    @ResultType(Admin.class)
    List<Admin> listAdmin();

}
