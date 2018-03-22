package site.shzu.demo.dao;

import site.shzu.demo.model.RolePermission;

public interface RolePermissionDao {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}