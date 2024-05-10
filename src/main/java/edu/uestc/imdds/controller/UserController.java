package edu.uestc.imdds.controller;


import edu.uestc.imdds.entitiy.User;
import edu.uestc.imdds.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.uestc.imdds.Utils.Utils.page;

@CrossOrigin(origins = {"http://localhost:5173", "null"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService ;

    @GetMapping
    @ResponseBody
    public Result getById(Integer id){
        User user =  userService.getById(id);
        return new Result(user!=null ? Code.GET_OK:Code.GET_ERR, user, user!=null ? "用户查询成功！":"未查找到用户信息！");
    }

    @PostMapping
    @ResponseBody
    public Result addUser(@RequestBody User user) {
        boolean flag = userService.addUser(user);
        return new Result(flag ? Code.ADD_OK:Code.ADD_ERR, flag, flag ? "用户添加成功！":"用户添加失败，可能已存在相同名称的用户！");
    }

    @DeleteMapping
    @ResponseBody
    public Result deleteById(Integer id){
        boolean flag = userService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR, flag, flag ? "用户删除成功！":"用户删除失败！");
    }

    @PutMapping
    @ResponseBody
    public Result updateUser(@RequestBody User user) {
        boolean flag = userService.updateUser(user);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR, flag, flag ? "用户信息修改成功！":"用户信息修改失败！");
    }

    @GetMapping("/all")
    @ResponseBody
    public Result getAll(String pageIndex,String pageSize){
        List<User> users =  userService.getAll();
        users = page(users,Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return new Result(!users.isEmpty() ? Code.GET_OK:Code.GET_ERR,!users.isEmpty() ? users:null, !users.isEmpty() ? "用户查询成功！":"未查找到用户信息！");
    }


    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);
        System.out.println(user.toString());
        boolean flag = user.getId()==null ? false : true;
        System.out.println(flag);
        return new Result(flag ? Code.LOGIN_OK:Code.LOGIN_ERR, user, flag ? "登录成功！":"登录失败，请检查用户名与密码！");
    }
}
