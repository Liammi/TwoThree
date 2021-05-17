package com.example.video.dao;

import com.example.video.pojo.User;
import com.example.video.vo.UserInfoVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    @Select("select * from user where user_name = #{username} and password = #{password}")
    @ResultType(User.class)
    User getByUserNameAndPassword(String username,String password);

    @Select("select * from user")
    List<User> listUser();

    @Select("select * from user where id=#{id}")
    @ResultType(UserInfoVO.class)
    UserInfoVO getUserById(Long id);

    @Select("select * from user where user_name=#{name}")
    @ResultType(User.class)
    User getUserByUserName(String name);

    @Select("select * from user where nick_name=#{nickName}")
    @ResultType(User.class)
    User getUserByNickName(String nickName);

    @Insert("insert into user(user_name, password, nick_name,avatar) VALUES (#{userName},#{password},#{nickName},#{avatar})")
    void saveUser(User user);

    @Update("update user set nick_name=#{nickName},avatar=#{avatar} where user_name=#{userName}")
    void updateUser(User user);

    @Update("update user set password=#{password} where user_name=#{userName}")
    void updateUserPassword(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);
}
