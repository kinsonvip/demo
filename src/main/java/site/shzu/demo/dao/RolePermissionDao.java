package site.shzu.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.shzu.demo.model.RolePermission;

import java.util.List;

@Mapper
public interface RolePermissionDao {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<String> selectPermissionByUserId(@Param(value = "userId") Integer userId);
}