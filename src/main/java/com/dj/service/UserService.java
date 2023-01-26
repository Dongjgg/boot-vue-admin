package com.dj.service;

import cn.hutool.core.util.StrUtil;
import com.dj.common.exception.CustomerException;
import com.dj.mapper.UserMapper;
import com.dj.pojo.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String DEFAULT_PASSWORD = "123";

    @Autowired
    private UserMapper userMapper;


    //登录
    public User login(String username,String password){
        //从数据库查询数据
        User user = userMapper.selectUser(username,password);
        if (user==null){
            logger.error("用户名或密码错误");
            throw new CustomerException("用户名或密码错误");
        }
        return user;
    }


    //注册
    public void register(String username,String password) throws CustomerException {
        User user = userMapper.selectUserByUserName(username);
        if (user!=null){  //表示数据库不存在重名对象
            logger.error("用户名重复");
            throw new CustomerException("用户名重复");
        }
        User saveUser = new User(username, password);
        userMapper.save(saveUser);
    }


    public List<User> findAll(String name,String phone) {
        return userMapper.selectAll(name,phone);
    }



    public void add(User user) {
        User res = userMapper.selectUserByUserName(user.getUsername());
        if (res!=null){
            throw new CustomerException("用户名重复");
        }
        if (StrUtil.isBlankIfStr(user.getPassword())){
            user.setPassword(DEFAULT_PASSWORD);
        }
        userMapper.add(user);
    }

    public void delById(int id) {
        userMapper.delById(id);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void pwdModify(String password,int id) {
        userMapper.pwdModify(password,id);
    }
}
