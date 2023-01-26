package com.dj.mapper;

import com.dj.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface UserMapper {
    User selectUser(@PathVariable("username") String username, @PathVariable("password") String password);

    User selectUserByUserName(String username);

    int save(User saveUser);

    List<User> selectAll(@PathVariable("name") String name,@PathVariable("phone") String phone);


    void add(User user);

    @Delete("delete from user where id = #{id}")
    void delById(@PathVariable("id") int id);

    void update(User user);

    void pwdModify(String password,int id);
}
