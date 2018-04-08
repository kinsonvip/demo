package site.shzu.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import site.shzu.demo.model.RolePermission;
import site.shzu.demo.model.User;

import java.util.List;

@Mapper
public interface RolePermissionDao {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<String> selectPermissionByUserId(User user);
}