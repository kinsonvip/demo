package site.shzu.demo.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import site.shzu.demo.model.Permission;
import site.shzu.demo.service.PermissionService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Kinson
 * @Description:
 * @Date: Created in 2018/04/07 21:31
 * @Version: 1.0
 */
public class ShiroService {
    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    PermissionService permissionService;

    /**
     * 初始化权限
     * @return 权限map
     */
    public Map<String, String> loadFilterChainDefinitions() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        List<Permission> pl = permissionService.selectAllPermission();
        for(Permission permission : pl){
            filterChainDefinitionMap.put(permission.getUrl(),permission.getDesc());
        }
        return filterChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                        .getObject();
            } catch (Exception e) {
                throw new RuntimeException(
                        "get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            System.out.println("更新权限成功！！");
        }
    }
}