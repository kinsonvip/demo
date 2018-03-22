package site.shzu.demo.dao;

import site.shzu.demo.model.UserRole;

public interface UserRoleDao {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}