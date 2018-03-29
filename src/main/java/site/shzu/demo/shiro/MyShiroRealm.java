package site.shzu.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import site.shzu.demo.dao.UserDao;
import site.shzu.demo.model.User;
import site.shzu.demo.service.UserService;

import java.util.*;

/**
 * @Author: Kinson
 * @Description: shiro身份校验核心类
 * @Date: Created in 2018/03/28 14:41
 * @Version: 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限授权方法：MyShiroRealm.doGetAuthenticationInfo()");
        User token = (User) SecurityUtils.getSubject().getPrincipal();
        String userId = String.valueOf(token.getId());
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
	    /*Map<String, Object> map = new HashMap<String, Object>();
	    map.put("user_id", userId);
	    List<SysRole> roleList = sysRoleService.selectByMap(map);
	    Set<String> roleSet = new HashSet<String>();
	    for(SysRole role : roleList){
		roleSet.add(role.getType());
	    }*/
        //实际开发，当前登录用户的角色和权限信息是从数据库来获取的，我这里写死是为了方便测试
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("100002");
        info.setRoles(roleSet);
        //根据用户ID查询权限（permission），放入到Authorization里。
	    /*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
	    Set<String> permissionSet = new HashSet<String>();
	    for(SysPermission Permission : permissionList){
		permissionSet.add(Permission.getName());
	    }*/
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     * @param authenticationToken
     * @return 验证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = new User();
        user.setName(token.getUsername());
        user.setPswd(String.valueOf(token.getPassword()));
        // 从数据库获取对应用户名密码的用户
        List<User> userList = userService.selectByUser(user);
        if(userList.size()!=0){
            user = userList.get(0);
            System.out.println("账号密码验证通过！");
        }
        if (userList.size()==0) {
            throw new AccountException("帐号或密码不正确！");
        }else if(user.getStatus()==0){
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("帐号已经被禁止登录！");
        }else{
            //更新登录时间 last login time
            user.setLastLoginTime(new Date());
            userService.updateById(user);
        }
        return new SimpleAuthenticationInfo(user, user.getPswd(), getName());
    }
}
