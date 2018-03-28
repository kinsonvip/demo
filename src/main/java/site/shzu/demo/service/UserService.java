package site.shzu.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.shzu.demo.dao.UserDao;
import site.shzu.demo.model.User;

import java.util.List;

/**
 * @Author: Kinson
 * @Description: User的Service类
 * @Date: Created in 2018/03/28 15:45
 * @Version: 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> selectByUser(User user) {
        return userDao.selectByUser(user);
    }

    public void updateById(User user){
        userDao.updateByPrimaryKey(user);
    }
}
