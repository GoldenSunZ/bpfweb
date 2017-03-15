package com.mapath.bpf.controller;
import com.mapath.bpf.model.LoginSimpleModel;
import com.mapath.bpf.utils.JsonInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/2/24.
 */
@Controller
public class LoginController {

    /**
     * 跳转到login.html页面
     * @return
     */
    @RequestMapping(value = "login.html")
    public String gotoLogin(){
        return "login";
    }

    /**
     * 登陆页面的验证
     * @param loginSimpleModel
     * @param session
     * @return
     */
    @RequestMapping(value = "loginin",produces = "application/json")
    @ResponseBody
    public JsonInfo login(LoginSimpleModel loginSimpleModel,HttpSession session){
        JsonInfo js=new JsonInfo();
        String name=loginSimpleModel.getName();
        String password=loginSimpleModel.getPassword();
        String messages="";
        String truename="root";
        String truepassword="root";
        if(name.equals("")) {
            messages="用户名不能为空!";
        }else if(!name.equals(truename)) {
            messages = "用户名不正确!";
        }else if(password.equals("")){
            messages="密码不能为空!";
        }else if(!password.equals(truepassword)){
            messages="密码不正确";
        }else{
            messages="登录成功!";
            session.setAttribute("user",truename); //把用户名放到session作用域
        }
        js.setMessage(messages);
        return js;
    }
}
