package site.shzu.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import site.shzu.demo.model.User;

import java.util.List;

@Mapper
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectByUser (User user);
}