package com.mapath.bpf.controller;
import com.mapath.bpf.model.LoginSimpleModel;
import com.mapath.bpf.model.ReqSimpleModel;
import com.mapath.bpf.service.AdminService;
import com.mapath.bpf.utils.JsonInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by Administrator on 2017/2/24.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "loginin",produces = "application/json")
    @ResponseBody
    public JsonInfo login(LoginSimpleModel loginSimpleModel){
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
        }
        js.setMessage(messages);
        return js;
    }
}
