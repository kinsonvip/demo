package site.shzu.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import site.shzu.demo.model.Orders;
import site.shzu.demo.model.User;
import site.shzu.demo.service.OrdersService;
import site.shzu.demo.util.Pager;
import site.shzu.demo.util.PagerUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @Author: Kinson
 * @Description: 控制类
 * @Date: Created in 2017/12/18 23:52
 * @Version: 1.0
 */

@Controller
@EnableAutoConfiguration
public class IndexController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/list", method= RequestMethod.GET)
    @ResponseBody
    public HashMap list(HttpServletRequest request){
        int pageNum = Integer.valueOf(request.getParameter("page"));
        int pageSize = Integer.valueOf(request.getParameter("recPerPage"));
        PageHelper.startPage(pageNum, pageSize);
        List<HashMap> list = ordersService.getAllOrders();

        List<HashMap> data = PagerUtil.formatAddOpetate(list);

        PageInfo page = new PageInfo(list);
        Pager pager = PagerUtil.getPager(page);

        HashMap<Object,Object> map = new HashMap<>();
        map.put("result","success");
        map.put("data",data);
        map.put("message","发生错误了！！");
        map.put("pager",pager);

        String rtnData = JSON.toJSONString(map);
        System.out.println(rtnData);
        return map;
    }

    @RequestMapping("/")
    public String test(){
        return "grid";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@Validated User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            StringBuffer msg = new StringBuffer();
            //获取错误字段集合
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            //获取本地locale,zh_CN
            Locale currentLocale = LocaleContextHolder.getLocale();
            for(FieldError fieldError : fieldErrors){
                String errorMessage = messageSource.getMessage(fieldError,currentLocale);
                msg.append(fieldError.getField()+":"+errorMessage+"......");
            }
//            Map<String,String> resultMap = new HashMap<String, String>();
            String info = msg.toString();
//            System.out.println(info);
//            resultMap.put("msg", msg.toString());
            return  info;
        }
        return "login in success:"+user.getUserName()+"##"+user.getPassWord();
    }
}
