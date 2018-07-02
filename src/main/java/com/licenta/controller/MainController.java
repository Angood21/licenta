package com.licenta.controller;

import com.licenta.model.News;
import com.licenta.model.Source;
import com.licenta.model.User;
import com.licenta.service.SourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Controller
@Scope("session")
public class MainController {

    @Autowired
    private SourceServiceImpl sourceService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView doHome(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        HttpSession session = getSession();
        User user = new User();
        session.setAttribute("user", user);
        List<Source> sources = sourceService.getAll();
        ArrayList<Feed> feeds = new ArrayList<>();
        for (Source source : sources) {

            feeds.add(new RSSFeedParser(source.getUrl()).readFeed());
        }

        List<News> news = new LinkedList<>();
        for (Feed feed : feeds) {
            for (News n : feed.getMessages())
                news.add(n);
        }

        Random random = new Random();
        List<News> topNews = new ArrayList<>();
        while (topNews.size() < 10) {
            int x = random.nextInt(news.size());
            if (!topNews.contains(news.get((x))))
                topNews.add(news.get(x));
        }
        model.addAttribute("news", topNews);
        return modelAndView;

    }

    public static HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
}
