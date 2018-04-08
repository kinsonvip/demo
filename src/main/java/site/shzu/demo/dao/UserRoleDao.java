package site.shzu.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import site.shzu.demo.model.User;
import site.shzu.demo.model.UserRole;

import java.util.List;

@Mapper
public interface UserRoleDao {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<String> selectRoleByUserId(User user);
}