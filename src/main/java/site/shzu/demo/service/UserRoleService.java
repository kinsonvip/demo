package site.shzu.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.shzu.demo.dao.UserRoleDao;
import site.shzu.demo.model.User;

import java.util.List;

/**
 * @Author: Kinson
 * @Description:
 * @Date: Created in 2018/04/08 12:14
 * @Version: 1.0
 */
@Service
public class UserRoleService {
    @Autowired
    UserRoleDao userRoleDao;

    public List<String> selectRoleByUserId(User user){
        return userRoleDao.selectRoleByUserId(user);
    }
}
