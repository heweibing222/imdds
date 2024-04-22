package edu.uestc.imdds.service.implement;


import edu.uestc.imdds.entitiy.User;
import edu.uestc.imdds.mapper.UserMapper;
import edu.uestc.imdds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getById(Integer id){
        return userMapper.getById(id);
    }
    @Override
    public User getByName(String name) {
        return userMapper.getByName(name);
    }
    @Override
    public  Boolean addUser(User user){
        // 禁止添加重名用户
        if(getByName(user.getUsername()) != null){
            return false;
        }
        int flag = userMapper.addUser(user);
        return flag == 1;
    }

    @Override
    public Boolean deleteById(Integer id) {
        int flag = userMapper.deleteById(id);
        return flag == 1;
    }

    @Override
    public Boolean updateUser(User user) {
        if(getById(user.getId()) == null){
            return false;
        }
        int flag = userMapper.updateUser(user);
        return flag == 1;
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public Boolean login(String username, String password) {
        User user = getByName(username);
        if(user == null) return false;
        return user.getPassword().equals(password);
    }


}
