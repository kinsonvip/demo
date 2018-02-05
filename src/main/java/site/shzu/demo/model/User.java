package site.shzu.demo.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author: Kinson
 * @Description: 用户实体类
 * @Date: Created in 2018/02/05 21:13
 * @Version: 1.0
 */
public class User {
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
