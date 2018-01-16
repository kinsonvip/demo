package site.shzu.demo.util;

import com.github.pagehelper.PageInfo;

/**
 * @Author: Kinson
 * @Description: 分页工具类
 * @Date: Created in 2018/1/15 18:51
 * @Version: 1.0
 */
public class PagerUtil {
    //获取pager
    public static Pager getPager(PageInfo pageInfo){
        return new Pager(pageInfo.getPageNum(),pageInfo.getTotal(),pageInfo.getPageSize());
    }
}
