package site.shzu.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import site.shzu.demo.model.User;
import site.shzu.demo.service.RolePermissionService;
import site.shzu.demo.service.UserRoleService;
import site.shzu.demo.service.UserService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Kinson
 * @Description: shiro身份校验核心类
 * @Date: Created in 2018/03/28 14:41
 * @Version: 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RolePermissionService rolePermissionService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //用户登录次数计数  redisKey 前缀
    private final String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private final String SHIRO_IS_LOCK = "shiro_is_lock_";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限授权方法：MyShiroRealm.doGetAuthorizationInfo()");
        //Integer userId = (Integer)SecurityUtils.getSubject().getPrincipal();
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
        List<String> roleList = userRoleService.selectRoleByUserId(user);
	    Set<String> roleSet = new HashSet<String>();
	    for(String role : roleList){
		roleSet.add(role);
	    }
        info.setRoles(roleSet);
        //根据用户ID查询权限（permission），放入到Authorization里。
        List<String> permissionList = rolePermissionService.selectPermissionByUserId(user);
	    Set<String> permissionSet = new HashSet<String>();
	    for(String urlDesc : permissionList){
		permissionSet.add(urlDesc);
	    }
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
        final String username = token.getUsername();
        final String password = String.valueOf(token.getPassword());
        //访问一次，计数一次
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.increment(SHIRO_LOGIN_COUNT+username, 1);
        //计数大于5时，设置用户被锁定一小时
        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+username))>=5){
            opsForValue.set(SHIRO_IS_LOCK+username, "LOCK");
            stringRedisTemplate.expire(SHIRO_IS_LOCK+username, 1, TimeUnit.HOURS);
        }
        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK+username))){
            throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
        }
        String md5Pswd = new Md5Hash(password, username, 2).toString();
        User user = new User();
        user.setName(username);
        user.setPswd(md5Pswd);
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
            //清空登录计数
            opsForValue.set(SHIRO_LOGIN_COUNT+username, "0");
        }
        return new SimpleAuthenticationInfo(user, String.valueOf(token.getPassword()), getName());
    }
}
