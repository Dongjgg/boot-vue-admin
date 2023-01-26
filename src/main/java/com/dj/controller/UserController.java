package com.dj.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dj.common.Result;
import com.dj.pojo.User;
import com.dj.service.UserService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Result<User> login(@RequestBody String userStr, HttpSession session){ //RequestBody 把后台发过来的json对象转成Java对象，也可以直接接受一个对象，框架会自动帮我们
        //{"username":"admin","password":"admin"}
        System.out.println(userStr);
        JSONObject userObj = JSONUtil.parseObj(userStr);
        //从前端获取数据
        String username = userObj.getStr("username");
        String password = userObj.getStr("password");
        User user = userService.login(username, password);
        if (user!=null){
            session.setAttribute("user",user);
        }
        return Result.success(user);
    }

    @RequestMapping("/logout")
    public void logOut(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("user");
        response.sendRedirect("/login.html");  //推荐后台跳转，防止缓存
    }

    @PostMapping("/register")
    public Result<Void> register(@RequestBody User user){
        //从前端获取数据
        userService.register(user.getUsername(),user.getPassword());
       return Result.success();
    }

    @GetMapping("/all")
    public Result<List<User>> findAll(@RequestParam(required = false) String name,@RequestParam(required = false)String phone){
        return Result.success(userService.findAll(name,phone));
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody User user){
        userService.add(user);
        return Result.success();
    }

    @GetMapping("/del")
    public Result<Void> del(int id){
        userService.delById(id);
        return Result.success();
    }

    @PutMapping("/add")
    public Result<Void> update(@RequestBody User user){  //@RequestBody处理application/json类型，一定要注意
        userService.update(user);
        return Result.success();
    }

    @PostMapping("/pwdmodify")
    public Result<Void> pwdModify(String password,Integer id){
        userService.pwdModify(password,id);
        return Result.success();
    }



}
