package com.example.video.dao;

import com.example.video.pojo.User;
import com.example.video.vo.UserInfoVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    @Select("select * from user where user_name = #{username} and password = #{password}")
    @ResultType(User.class)
    User getByUserNameAndPassword(String username,String password);

    @Select("select * from user where id=#{id}")
    @ResultType(UserInfoVO.class)
    UserInfoVO getUserById(Long id);

    @Select("select * from user where user_name=#{name}")
    @ResultType(User.class)
    User getUserByUserName(String name);

    @Select("select * from user where nick_name=#{nickName}")
    @ResultType(User.class)
    User getUserByNickName(String nickName);

    @Insert("insert into user(user_name, password, nick_name) VALUES (#{userName},#{password},#{nickName})")
    void saveUser(User user);
}
