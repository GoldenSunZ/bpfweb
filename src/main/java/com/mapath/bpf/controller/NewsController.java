package com.mapath.bpf.controller;

import com.mapath.bpf.model.NewsModel;
import com.mapath.bpf.service.NewsService;
import com.mapath.bpf.service.impl.NewsServiceImpl;
import com.mapath.bpf.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouxiaobo on 2017/2/28.
 */

@Controller()
public class NewsController {

    @Autowired
    private NewsService newsService;


}
