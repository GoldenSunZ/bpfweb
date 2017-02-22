package com.mapath.bpf.controller;

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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ulongx on 2017/2/21.
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Configuration configuration;

    @Autowired
    AdminService adminService;

    @RequestMapping(value = {"index.html","/"})
    public String indexPage(){

        return "index";
    }

    @RequestMapping(value = {"applications.html"})
    public String applicationsPage(){

        return "applications";
    }

    @RequestMapping(value = {"vision.html"})
    public String visionPage(){

        return "vision";
    }

    @RequestMapping(value = {"expertise.html"})
    public String expertisePage(){

        return "expertise";
    }

    @RequestMapping(value = {"transparency.html"})
    public String transparencyPage(){

        return "transparency";
    }

    @RequestMapping(value = {"news.html"})
    public String newsPage(){

        return "news";
    }

    @RequestMapping(value = {"contact.html"})
    public String contactPage(){

        return "contact";
    }

    @RequestMapping(value = "reqsimple")
    @ResponseBody
    public JsonInfo requestSimple(ReqSimpleModel reqSimpleModel){
        JsonInfo info = new JsonInfo();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "123");
        model.put("subscriptionDate",new Date());
        try {
            Template t = configuration.getTemplate("mailtemplates/email-html.ftl");
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            logger.debug(content);
            if(adminService.sendHtmlMail("xuyunlong@mapath.com","索取样品",content)){
                info.setMessage("邮件发送成功");
                info.setCode(JsonInfo.OK);
            }else{
                info.setMessage("邮件发送失败，请检查参数配置");
                info.setCode(JsonInfo.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            info.setMessage("邮件发送失败，请检查参数配置");
            info.setCode(JsonInfo.ERROR);
        }

        return info;
    }
}
