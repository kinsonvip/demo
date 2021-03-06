package site.shzu.demo.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import site.shzu.demo.model.User;
import site.shzu.demo.service.OrdersService;
import site.shzu.demo.util.Pager;
import site.shzu.demo.util.PagerUtil;
import site.shzu.demo.vcode.Captcha;
import site.shzu.demo.vcode.GifCaptcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("/login")
    public String test(){
        return "login";
    }

    @RequestMapping("/grid")
    public String grid(){
        return "grid";
    }

/*    @RequestMapping("/login")
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
        return "login in success:"+user.getName()+"##"+user.getPswd();
    }*/

    /**
     * ajax登录请求
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(value="/ajaxLogin",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> ajaxLogin(String userName, String passWord,String vcode,Boolean rememberMe,Model model) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(vcode==null||vcode==""){
            resultMap.put("status", 500);
            resultMap.put("message", "验证码不能为空！");
            return resultMap;
        }

        Session session = SecurityUtils.getSubject().getSession();
        //转化成小写字母
        vcode = vcode.toLowerCase();
        String v = (String) session.getAttribute("_code");
        //还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
        //session.removeAttribute("_come");
        if(!vcode.equals(v)){
            resultMap.put("status", 500);
            resultMap.put("message", "验证码错误！");
            return resultMap;
        }

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord, rememberMe);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }

    //被踢出后跳转的页面
    @RequestMapping(value="kickout")
    public String kickout() {
        return "kickout";
    }

    /**
     * 获取验证码（Gif版本）
     */
    @RequestMapping(value="getGifCode",method=RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146,33,4);
            //输出
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            //存入Session
            session.setAttribute("_code",captcha.text().toLowerCase());
        } catch (Exception e) {
            System.err.println("获取验证码异常："+e.getMessage());
        }
    }
}
