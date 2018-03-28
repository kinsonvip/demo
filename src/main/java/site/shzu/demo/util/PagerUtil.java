package site.shzu.demo.util;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Kinson
 * @Description: 分页工具类
 * @Date: Created in 2018/1/15 18:51
 * @Version: 1.0
 */
public class PagerUtil {
    /**
     * 获取pager信息
     * @param pageInfo 传入PageHelper里的pageInfo
     * @return pager类(前台zui表格分页的信息)
     */
    @org.jetbrains.annotations.NotNull
    public static Pager getPager(PageInfo pageInfo){
        return new Pager(pageInfo.getPageNum(),pageInfo.getTotal(),pageInfo.getPageSize());
    }

    public static List<HashMap> formatAddOpetate(List<HashMap> list){
        List rtn = new ArrayList();
        for(HashMap map : list){
            //map.put("operate","<button class=\"btn btn-sm btn-info \" type=\"button\"><i class=\"icon icon-edit\"></i>编辑</button>");
            rtn.add(map);
        }
        return rtn;
    }
}
