package site.shzu.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import site.shzu.demo.model.Orders;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface OrdersDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<HashMap> selectAllOrders();

    Orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}