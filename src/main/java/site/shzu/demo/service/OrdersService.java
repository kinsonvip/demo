package site.shzu.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.shzu.demo.dao.OrdersDao;
import site.shzu.demo.model.Orders;

import java.util.List;

/**
 * @Author: Kinson
 * @Description: Orders的service类
 * @Date: Created in 2018/1/10 9:15
 * @Version: 1.0
 */
@Service
public class OrdersService {
    @Autowired
    OrdersDao ordersDao;

    public List<Orders> getAllOrders(){
        return ordersDao.selectAllOrders();
    }
}
