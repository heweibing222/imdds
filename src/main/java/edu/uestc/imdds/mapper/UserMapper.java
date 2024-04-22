package edu.uestc.imdds.mapper;

import edu.uestc.imdds.entitiy.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User getById(Integer id);

    User getByName(String Name);

    int addUser(User user);

    int deleteById(Integer id);

    int updateUser(User user);

    List<User> getAll();

}
