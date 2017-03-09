package com.mapath.bpf.controller;

import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.model.NewsPage;
import com.mapath.bpf.model.ReqSimpleModel;
import com.mapath.bpf.service.AdminService;
import com.mapath.bpf.service.NewsService;
import com.mapath.bpf.utils.JsonInfo;
import com.mapath.util.pagination.model.DataGrid;
import com.mapath.util.pagination.model.PageInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    NewsService newsService;

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

    /*跳转到新闻展示页面*/
    @RequestMapping(value = {"news.html"})
    public String newsPage(NewsPage newsPage, Model model){
        if(newsPage.getPageInfo() == null){
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPage(1);
            newsPage.setPageInfo(pageInfo);
        }
        DataGrid<NewsModel> dataGrid = newsService.newslist(newsPage);
        /*以下有问题*/
        String comments="";
        for (Object neww:dataGrid.getData()){
            NewsModel mo= (NewsModel) neww;
            comments=mo.getComments();
            String regx="/^<img src=\".*\">$/";
            Pattern pattern=Pattern.compile(regx);
            Matcher matcher=pattern.matcher(comments);
            String img=matcher.group(1);
            mo.setPicture("<img src="+'"'+img+'"'+">");
        }

        model.addAttribute("result",dataGrid);
        return "news";
    }

    @RequestMapping(value = {"contact.html"})
    public String contactPage(){

        return "contact";
    }

    @RequestMapping(value = "reqsimple",produces="application/json")
    @ResponseBody
    public JsonInfo requestSimple(ReqSimpleModel reqSimpleModel){
        JsonInfo info = new JsonInfo();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", reqSimpleModel.getName());
        model.put("subscriptionDate",new Date());
        model.put("emailAddress",reqSimpleModel.getEmailAddress());
        model.put("state",reqSimpleModel.getState());
        model.put("category",reqSimpleModel.getCategory());
        model.put("phoneNumber",reqSimpleModel.getPhoneNumber());
        model.put("projectContext",reqSimpleModel.getProjectContext());
        model.put("companyName",reqSimpleModel.getCompanyName());

        try {
            Template t = configuration.getTemplate("mailtemplates/email-html.ftl");
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            logger.debug(content);
            if(adminService.sendHtmlMail("sales@bluepacificchina.com","索取样品"+reqSimpleModel.getName(),content)){
                info.setMessage("索取样品信息提交成功!");
                info.setCode(JsonInfo.OK);
            }else{
                info.setMessage("索取失败!");
                info.setCode(JsonInfo.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            info.setMessage("索取失败!!");
            info.setCode(JsonInfo.ERROR);
        }

        return info;
    }
}
